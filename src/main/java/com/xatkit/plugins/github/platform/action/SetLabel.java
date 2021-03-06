package com.xatkit.plugins.github.platform.action;

import com.jcabi.github.Issue;
import com.xatkit.core.XatkitException;
import com.xatkit.core.platform.action.RuntimeAction;
import com.xatkit.execution.StateContext;
import com.xatkit.plugins.github.platform.GithubPlatform;
import lombok.NonNull;

import java.io.IOException;
import java.util.Arrays;

import static fr.inria.atlanmod.commons.Preconditions.checkArgument;

/**
 * A {@link RuntimeAction} that set the provided {@code label} to the given {@code issue}.
 * <p>
 * This action creates a new label if the provided one doesn't match a the defined labels on Github.
 * <p>
 * <b>Note:</b> this class requires that its containing {@link GithubPlatform} has been loaded with a valid Github
 * credentials in order to authenticate the bot and access the Github API.
 */
public class SetLabel extends RuntimeAction<GithubPlatform> {

    /**
     * The {@link Issue} to set a label to.
     */
    private Issue issue;

    /**
     * The label to set to the provided {@link Issue}.
     */
    private String label;

    /**
     * Constructs a new {@link SetLabel} with the provided {@code runtimePlatform}, {@code session}, {@code issue},
     * and {@code username}.
     *
     * @param platform the {@link GithubPlatform} containing this action
     * @param context  the {@link StateContext} associated to this action
     * @param issue    the {@link Issue} to set a label to
     * @param label    the label to set to the provided {@link Issue}
     * @throws NullPointerException     if the provided {@code runtimePlatform}, {@code session}, or {@code issue} is
     *                                  {@code null}
     * @throws IllegalArgumentException if the provided {@code label} is {@code null} or empty
     */
    public SetLabel(@NonNull GithubPlatform platform, @NonNull StateContext context, @NonNull Issue issue,
                    @NonNull String label) {
        super(platform, context);
        checkArgument(!label.isEmpty(), "Cannot construct a %s action with the provided label, " +
                "expected a non-empty label, found %s", this.getClass().getSimpleName(), label);
        this.issue = issue;
        this.label = label;
    }

    /**
     * Sets the provided {@code label} to the given {@link Issue}.
     * <p>
     * This action creates a new label if the provided one doesn't match the defined labels on Github.
     *
     * @return the assigned label
     * @throws XatkitException if an error occurred when setting the {@code label} of the provided {@code issue}
     */
    @Override
    protected Object compute() {
        try {
            issue.labels().add(Arrays.asList(label));
            return label;
        } catch (IOException e) {
            throw new XatkitException("Cannot add the label to the issue, see attached exception", e);
        }
    }
}
