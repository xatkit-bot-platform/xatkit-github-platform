package com.xatkit.plugins.github.platform.action;

import com.jcabi.github.Coordinates;
import com.jcabi.github.Issue;
import com.jcabi.github.Repo;
import com.xatkit.core.XatkitException;
import com.xatkit.core.platform.action.RuntimeAction;
import com.xatkit.execution.StateContext;
import com.xatkit.plugins.github.platform.GithubPlatform;
import fr.inria.atlanmod.commons.log.Log;
import lombok.NonNull;

import javax.annotation.Nullable;
import java.io.IOException;

import static java.util.Objects.isNull;

/**
 * A {@link RuntimeAction} that opens a new issue on the given {@code repository} with the provided {@code issueTitle}
 * and {@code issueContent}.
 * <p>
 * This class relies on the {@link GithubPlatform} to access the Github API and authenticate the bot.
 * <p>
 * <b>Note: </b> this class requires that its containing {@link GithubPlatform} has been loaded with valid Github
 * credentials in order to authenticate the bot and access the Github API.
 *
 * @see GithubPlatform
 */
public class OpenIssue extends RuntimeAction<GithubPlatform> {

    /**
     * The Github user managing the repository to open an issue in.
     */
    private String user;

    /**
     * The Github repository to open an issue in.
     */
    private String repository;

    /**
     * The title of the issue to open.
     */
    private String issueTitle;

    /**
     * The content of the issue to open.
     */
    private String issueContent;

    /**
     * Constructs a new {@link OpenIssue} with the provided {@code runtimePlatform}, {@code session}, {@code user},
     * {@code repository}, {@code issueTitle}, and {@code issueContent}.
     * <p>
     * This constructor accepts {@code null} as its {@code issueContent} parameter. In this case the opened issue
     * will have an empty content.
     *
     * @param platform the {@link GithubPlatform} containing this action
     * @param context          the {@link StateContext} associated to this action
     * @param user             the Github user managing the repository to open an issue in
     * @param repository       the Github repository to open an issue in
     * @param issueTitle       the title of the issue to open
     * @param issueContent     the content of the issue to open
     * @throws NullPointerException if the provided {@code platform}, {@code context}, {@code user}, {@code
     *                              repository}, or {@code issueTitle} is {@code null}
     */
    public OpenIssue(@NonNull GithubPlatform platform, @NonNull StateContext context, @NonNull String user,
                     @NonNull String repository, @NonNull String issueTitle, @Nullable String issueContent) {
        super(platform, context);
        this.user = user;
        this.repository = repository;
        this.issueTitle = issueTitle;
        this.issueContent = issueContent;
        if (isNull(issueContent)) {
            Log.warn("{0} initialized with an empty issue content", this.getClass().getSimpleName());
        }
    }

    /**
     * Opens a new issue on the given {@code repository} with the provided {@code issueTitle} and {@code issueContent}.
     * <p>
     * This method relies on the containing {@link GithubPlatform} to access the Github API, and will throw a
     * {@link XatkitException} if the Xatkit {@link org.apache.commons.configuration2.Configuration} does not define
     * valid Github authentication credentials.
     *
     * @return the created {@link Issue}
     * @throws XatkitException if the {@link GithubPlatform} does not hold a valid Github API client (i.e. if the
     *                         Xatkit {@link org.apache.commons.configuration2.Configuration} does not define valid
     *                         Github authentication credentials)
     * @see GithubPlatform#getGithubClient()
     */
    @Override
    protected Object compute() {
        Repo githubRepo = this.runtimePlatform.getGithubClient().repos().get(new Coordinates.Simple(user, repository));
        try {
            Issue githubIssue = githubRepo.issues().create(issueTitle, issueContent);
            return new Issue.Smart(githubIssue);
        } catch (IOException e) {
            throw new XatkitException("Cannot open the Github issue, see attached exception", e);
        }
    }
}
