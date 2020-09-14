package com.xatkit.plugins.github.platform.io;

import com.xatkit.core.platform.io.JsonEventMatcher;
import com.xatkit.core.platform.io.WebhookEventProvider;
import com.xatkit.core.server.JsonRestHandler;
import com.xatkit.core.server.RestHandlerFactory;
import com.xatkit.execution.StateContext;
import com.xatkit.intent.EventDefinition;
import com.xatkit.intent.EventInstance;
import com.xatkit.plugins.github.platform.GithubPlatform;
import org.apache.commons.configuration2.Configuration;

import static com.xatkit.dsl.DSL.event;

public class GithubWebhookEventProvider extends WebhookEventProvider<GithubPlatform, JsonRestHandler> {

    private final static String GITHUB_EVENT_HEADER_KEY = "X-GitHub-Event";

    private final static String ENDPOINT_URI = "/github";

    private JsonEventMatcher matcher;

    /**
     * Constructs a {@link GithubWebhookEventProvider} and binds it to the provided {@code githubPlatform}.
     *
     * @param githubPlatform the {@link GithubPlatform} managing this provider
     */
    public GithubWebhookEventProvider(GithubPlatform githubPlatform) {
        super(githubPlatform);
    }

    /**
     * {@inheritDoc}
     * <p>
     * This method sets the internal matchers used to create {@link EventInstance} from incoming Github requests.
     * Received events won't be processed until this method is invoked.
     */
    @Override
    public void start(Configuration configuration) {
        super.start(configuration);
        matcher = new JsonEventMatcher();
        JsonEventMatcher.HeaderValue issueHeader = JsonEventMatcher.HeaderValue.of(GITHUB_EVENT_HEADER_KEY, "issues");
        matcher.addMatchableEvent(issueHeader, JsonEventMatcher.FieldValue.of("action", "opened"), IssueOpened);
        matcher.addMatchableEvent(issueHeader, JsonEventMatcher.FieldValue.of("action", "edited"), IssueEdited);
        matcher.addMatchableEvent(issueHeader, JsonEventMatcher.FieldValue.of("action", "closed"), IssueClosed);
        matcher.addMatchableEvent(issueHeader, JsonEventMatcher.FieldValue.of("action", "reopened"), IssueReopened);
        matcher.addMatchableEvent(issueHeader, JsonEventMatcher.FieldValue.of("action", "assigned"), IssueAssigned);
        matcher.addMatchableEvent(issueHeader, JsonEventMatcher.FieldValue.of("action", "unassigned"), IssueUnassigned);
        matcher.addMatchableEvent(issueHeader, JsonEventMatcher.FieldValue.of("action", "labeled"), IssueLabeled);
        matcher.addMatchableEvent(issueHeader, JsonEventMatcher.FieldValue.of("action", "unlabeled"), IssueUnlabeled);
        matcher.addMatchableEvent(issueHeader, JsonEventMatcher.FieldValue.of("action", "milestoned"), IssueMilestoned);
        matcher.addMatchableEvent(issueHeader, JsonEventMatcher.FieldValue.of("action", "demilestoned"),
                IssueDemilestoned);
        JsonEventMatcher.HeaderValue issueCommentHeader = JsonEventMatcher.HeaderValue.of(GITHUB_EVENT_HEADER_KEY,
                "issue_comment");
        // Issue Comments
        // TODO: should we differentiate pull requests and issues?
        matcher.addMatchableEvent(issueCommentHeader, JsonEventMatcher.FieldValue.of("action", "created"),
                IssueCommentCreated);
        matcher.addMatchableEvent(issueCommentHeader, JsonEventMatcher.FieldValue.of("action", "edited"),
                IssueCommentEdited);
        matcher.addMatchableEvent(issueCommentHeader, JsonEventMatcher.FieldValue.of("action", "deleted"),
                IssueCommentDeleted);
        // Labels
        JsonEventMatcher.HeaderValue labelHeader = JsonEventMatcher.HeaderValue.of(GITHUB_EVENT_HEADER_KEY, "label");
        matcher.addMatchableEvent(labelHeader, JsonEventMatcher.FieldValue.of("action", "created"), LabelCreated);
        matcher.addMatchableEvent(labelHeader, JsonEventMatcher.FieldValue.of("action", "edited"), LabelEdited);
        matcher.addMatchableEvent(labelHeader, JsonEventMatcher.FieldValue.of("action", "deleted"), LabelDeleted);
        // Pull Requests
        JsonEventMatcher.HeaderValue pullRequestHeader = JsonEventMatcher.HeaderValue.of(GITHUB_EVENT_HEADER_KEY,
                "pull_request");
        matcher.addMatchableEvent(pullRequestHeader, JsonEventMatcher.FieldValue.of("action", "opened"),
                PullRequestOpened);
        matcher.addMatchableEvent(pullRequestHeader, JsonEventMatcher.FieldValue.of("action", "edited"),
                PullRequestEdited);
        // TODO: differentiate between merged and not-merged pull requests
        matcher.addMatchableEvent(pullRequestHeader, JsonEventMatcher.FieldValue.of("action", "closed"),
                PullRequestClosed);
        matcher.addMatchableEvent(pullRequestHeader, JsonEventMatcher.FieldValue.of("action", "reopened"),
                PullRequestReopened);
        matcher.addMatchableEvent(pullRequestHeader, JsonEventMatcher.FieldValue.of("action", "assigned"),
                PullRequestAssigned);
        matcher.addMatchableEvent(pullRequestHeader, JsonEventMatcher.FieldValue.of("action", "unassigned"),
                PullRequestUnassigned);
        matcher.addMatchableEvent(pullRequestHeader, JsonEventMatcher.FieldValue.of("action", "labeled"),
                PullRequestLabeled);
        matcher.addMatchableEvent(pullRequestHeader, JsonEventMatcher.FieldValue.of("action", "unlabeled"),
                PullRequestUnlabeled);
        matcher.addMatchableEvent(pullRequestHeader, JsonEventMatcher.FieldValue.of("action", "review_requested"),
                PullRequestReviewRequested);
        matcher.addMatchableEvent(pullRequestHeader, JsonEventMatcher.FieldValue.of("action", "review_request_removed"
        ), PullRequestReviewRequestRemoved);
        // Push
        JsonEventMatcher.HeaderValue pushHeader = JsonEventMatcher.HeaderValue.of(GITHUB_EVENT_HEADER_KEY, "push");
        matcher.addMatchableEvent(pushHeader, JsonEventMatcher.FieldValue.EMPTY_FIELD_VALUE, Push);
        // Stars
        JsonEventMatcher.HeaderValue starRequestHeader = JsonEventMatcher.HeaderValue.of(GITHUB_EVENT_HEADER_KEY,
                "star");
        matcher.addMatchableEvent(starRequestHeader, JsonEventMatcher.FieldValue.of("action", "created"),
                StarCreated);
        matcher.addMatchableEvent(starRequestHeader, JsonEventMatcher.FieldValue.of("action", "deleted"),
                StarDeleted);

    }

    @Override
    public String getEndpointURI() {
        return ENDPOINT_URI;
    }

    @Override
    protected JsonRestHandler createRestHandler() {
        return RestHandlerFactory.createJsonRestHandler((headers, params, content) -> {
            EventInstance eventInstance = matcher.match(headers, content);
            StateContext context  = this.xatkitBot.getOrCreateContext("github");
            this.sendEventInstance(eventInstance, context);
            return null;
        });
    }

    @Override
    public void run() {
        synchronized (this) {
            try {
                wait();
            } catch (InterruptedException e) {

            }
        }
    }

    public static EventDefinition IssueOpened = createJsonEvent("Issue_Opened");

    public static EventDefinition IssueEdited = createJsonEvent("Issue_Edited");

    public static EventDefinition IssueClosed = createJsonEvent("Issue_Closed");

    public static EventDefinition IssueReopened = createJsonEvent("Issue_Reopened");

    public static EventDefinition IssueAssigned = createJsonEvent("Issue_Assigned");

    public static EventDefinition IssueUnassigned = createJsonEvent("Issue_Unassigned");

    public static EventDefinition IssueLabeled = createJsonEvent("Issue_Labeled");

    public static EventDefinition IssueUnlabeled = createJsonEvent("Issue_Unlabeled");

    public static EventDefinition IssueMilestoned = createJsonEvent("Issue_Milestoned");

    public static EventDefinition IssueDemilestoned = createJsonEvent("Issue_Demilestoned");

    public static EventDefinition IssueCommentCreated = createJsonEvent("Issue_Comment_Created");

    public static EventDefinition IssueCommentEdited = createJsonEvent("Issue_Comment_Edited");

    public static EventDefinition IssueCommentDeleted = createJsonEvent("Issue_Comment_Deleted");

    public static EventDefinition PullRequestCommentCreated = createJsonEvent("Pull_Request_Comment_Created");

    public static EventDefinition PullRequestCommentEdited = createJsonEvent("Pull_Request_Comment_Edited");

    public static EventDefinition PullRequestCommentDeleted = createJsonEvent("Pull_Request_Comment_Deleted");

    public static EventDefinition WikiPageCreated = createJsonEvent("Wiki_Page_Created");

    public static EventDefinition WikiPageEdited = createJsonEvent("Wiki_Page_Edited");

    public static EventDefinition PullRequestOpened = createJsonEvent("Pull_Request_Opened");

    public static EventDefinition PullRequestClosed = createJsonEvent("Pull_Request_Closed");

    public static EventDefinition PullRequestEdited = createJsonEvent("Pull_Request_Edited");

    public static EventDefinition PullRequestReopened = createJsonEvent("Pull_Request_Reopened");

    public static EventDefinition PullRequestAssigned = createJsonEvent("Pull_Request_Assigned");

    public static EventDefinition PullRequestUnassigned = createJsonEvent("Pull_Request_Unassigned");

    public static EventDefinition PullRequestReviewRequested = createJsonEvent("Pull_Request_Review_Requested");

    public static EventDefinition PullRequestReviewRequestRemoved = createJsonEvent(
            "Pull_Request_Review_Request_Removed");

    public static EventDefinition PullRequestLabeled = createJsonEvent("Pull_Request_Labeled");

    public static EventDefinition PullRequestUnlabeled = createJsonEvent("Pull_Request_Unlabeled");

    public static EventDefinition LabelCreated = createJsonEvent("Label_Created");

    public static EventDefinition LabelEdited = createJsonEvent("Label_Edited");

    public static EventDefinition LabelDeleted = createJsonEvent("Label_Deleted");

    public static EventDefinition Push = createJsonEvent("Push");

    public static EventDefinition StarCreated = createJsonEvent("Star_Created");

    public static EventDefinition StarDeleted = createJsonEvent("Star_Deleted");


    private static EventDefinition createJsonEvent(String name) {
        return event(name)
                .context("data")
                .parameter("json")
                .getEventDefinition();
    }

}
