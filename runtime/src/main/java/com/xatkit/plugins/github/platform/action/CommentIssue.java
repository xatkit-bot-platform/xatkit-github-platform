package com.xatkit.plugins.github.platform.action;

import com.jcabi.github.Comment;
import com.jcabi.github.Issue;
import com.xatkit.core.XatkitException;
import com.xatkit.core.platform.action.RuntimeAction;
import com.xatkit.execution.StateContext;
import com.xatkit.plugins.github.platform.GithubPlatform;
import lombok.NonNull;

import java.io.IOException;

import static fr.inria.atlanmod.commons.Preconditions.checkArgument;

/**
 * A {@link RuntimeAction} that creates a new comment on the provided {@link Issue}.
 * <p>
 * This class relies on the {@link GithubPlatform} to access the Github API and authenticate the bot.
 * <p>
 * <b>Note:</b> this class requires that its containing {@link GithubPlatform} has been loaded with valid Github
 * credentials in order to authenticate the bot and access the Github API.
 *
 * @see GithubPlatform
 */
public class CommentIssue extends RuntimeAction<GithubPlatform> {

    /**
     * The {@link Issue} to create a comment for.
     */
    private Issue issue;

    /**
     * The content of the comment to post on the {@link Issue}.
     */
    private String commentContent;


    /**
     * Constructs a new {@link CommentIssue} with the provided {@code platform}, {@code context}, {@code issue}, and
     * {@code commentContent).
     *
     * @param platform       the {@link GithubPlatform} containing this action
     * @param context        the {@link StateContext} associated to this action
     * @param issue          the {@link Issue} to create a comment for
     * @param commentContent the content of the comment to post on the {@link Issue}
     * @throws NullPointerException     if the provided {@code platform}, {@code context}, {@code issue}, or {@code
     *                                  commentContent} is {@code null}
     * @throws IllegalArgumentException if the provided {@code commentContent} is empty
     */
    public CommentIssue(@NonNull GithubPlatform platform, @NonNull StateContext context, @NonNull Issue issue,
                        @NonNull String commentContent) {
        super(platform, context);
        checkArgument(!commentContent.isEmpty(), "Cannot construct a %s action with the provided comment, expected a " +
                "non-empty comment, found %s", this.getClass().getSimpleName(), commentContent);
        this.issue = issue;
        this.commentContent = commentContent;
    }

    /**
     * Creates a new comment on the provided {@link Issue} with the given {@code commentContent}.
     *
     * @return the created {@link Comment}
     * @throws XatkitException if the {@link GithubPlatform} does not hold a valid Github API client (i.e. if the
     *                         Xatkit {@link org.apache.commons.configuration2.Configuration} does not define valid
     *                         Github authentication credentials)
     * @see GithubPlatform#getGithubClient()
     */
    @Override
    protected Object compute() {
        try {
            Comment comment = issue.comments().post(commentContent);
            return comment;
        } catch (IOException e) {
            throw new XatkitException("Cannot retrieve the Github issue, see attached exception", e);
        }
    }
}
