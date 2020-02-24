Xatkit Github Platform
=====

[![License Badge](https://img.shields.io/badge/license-EPL%202.0-brightgreen.svg)](https://opensource.org/licenses/EPL-2.0)
[![Build Status](https://travis-ci.com/xatkit-bot-platform/xatkit-github-platform.svg?branch=master)](https://travis-ci.com/xatkit-bot-platform/xatkit-github-platform)
[![Wiki Badge](https://img.shields.io/badge/doc-wiki-blue)](https://github.com/xatkit-bot-platform/xatkit/wiki/Xatkit-Github-Platform)

Receive events and performs action on Github from your Xatkit execution model.

## Providers

The Github platform define the following providers:

| Provider                   | Type  | Context Parameters | Description                                                  |
| -------------------------- | ----- | ------------------ | ------------------------------------------------------------ |
| GithubWebhookEventProvider | Event | -                  | Receive webhook events from the Github API and translate them into Xatkit-compatible events. |

### GithubWebhookEventProvider Events

| Event                               | Context                | Parameters | Description                                                  |
| ----------------------------------- | ---------------------- | ---------- | ------------------------------------------------------------ |
| Issue_Opened                        | `issue`                | [See the platform file](https://github.com/xatkit-bot-platform/xatkit-github-platform/blob/master/platform/GithubPlatform.platform)       | Event sent when a new issue is opened in the repository.     |
| Issue_Edited                        | `issue`                | [See the platform file](https://github.com/xatkit-bot-platform/xatkit-github-platform/blob/master/platform/GithubPlatform.platform)       | Event sent when an issue is edited.                          |
| Issue_Closed                        | `issue`                | [See the platform file](https://github.com/xatkit-bot-platform/xatkit-github-platform/blob/master/platform/GithubPlatform.platform)       | Event sent when an issue is closed.                          |
| Issue_Reopened                      | `issue`                | [See the platform file](https://github.com/xatkit-bot-platform/xatkit-github-platform/blob/master/platform/GithubPlatform.platform)       | Event sent when an issue is reopened.                        |
| Issue_Assigned                      | `issue`                | [See the platform file](https://github.com/xatkit-bot-platform/xatkit-github-platform/blob/master/platform/GithubPlatform.platform)       | Event sent when an issue is assigned.                        |
| Issue_Unassigned                    | `issue`                | [See the platform file](https://github.com/xatkit-bot-platform/xatkit-github-platform/blob/master/platform/GithubPlatform.platform)       | Event sent when an issue is unassigned.                      |
| Issue_Labeled                       | `issue`                | [See the platform file](https://github.com/xatkit-bot-platform/xatkit-github-platform/blob/master/platform/GithubPlatform.platform)       | Event sent when an issue is labeled. **Note**: issues that are labeled with multiple labels produce multiple *Issue_Labeled* events. |
| Issue_Unlabeled                     | `issue`                | [See the platform file](https://github.com/xatkit-bot-platform/xatkit-github-platform/blob/master/platform/GithubPlatform.platform)       | Event sent when an issue is unlabeled. **Note**: issues that are unlabeled from multiple labels produce multiple *Issue_Unlabeled* events. |
| Issue_Milestoned                    | `issue`                | [See the platform file](https://github.com/xatkit-bot-platform/xatkit-github-platform/blob/master/platform/GithubPlatform.platform)       | Event sent when an issue is added to a milestone.            |
| Issue_Demilestoned                  | `issue`                | [See the platform file](https://github.com/xatkit-bot-platform/xatkit-github-platform/blob/master/platform/GithubPlatform.platform)       | Event sent when an issue is removed from a milestone.        |
| Issue_Comment_Created               | `issue_comment`        | [See the platform file](https://github.com/xatkit-bot-platform/xatkit-github-platform/blob/master/platform/GithubPlatform.platform)       | Event sent when a new comment is created on an issue.        |
| Issue_Comment_Edited                | `issue_comment`        | [See the platform file](https://github.com/xatkit-bot-platform/xatkit-github-platform/blob/master/platform/GithubPlatform.platform)       | Event sent when a comment on an issue is edited.             |
| Issue_Comment_Deleted               | `issue_comment`        | [See the platform file](https://github.com/xatkit-bot-platform/xatkit-github-platform/blob/master/platform/GithubPlatform.platform)       | Event sent when a comment on an issue is deleted.            |
| Pull_Request_Comment_Created        | `pull_request_comment` | [See the platform file](https://github.com/xatkit-bot-platform/xatkit-github-platform/blob/master/platform/GithubPlatform.platform)       | Event sent when a new comment is created on a pull request.  |
| Pull_Request_Comment_Edited         | `pull_request_comment` | [See the platform file](https://github.com/xatkit-bot-platform/xatkit-github-platform/blob/master/platform/GithubPlatform.platform)       | Event sent when a comment on a pull request is edited.       |
| Pull_Request_Comment_Deleted        | `pull_request_comment` | [See the platform file](https://github.com/xatkit-bot-platform/xatkit-github-platform/blob/master/platform/GithubPlatform.platform)       | Event sent when a comment on a pull request is deleted.      |
| Wiki_Page_Created                   | `wiki`                 | [See the platform file](https://github.com/xatkit-bot-platform/xatkit-github-platform/blob/master/platform/GithubPlatform.platform)       | Event sent when a new page is created in the repository's wiki. |
| Wiki_Page_Edited                    | `wiki`                 | [See the platform file](https://github.com/xatkit-bot-platform/xatkit-github-platform/blob/master/platform/GithubPlatform.platform)       | Event sent when a page from the repository's wiki is updated. |
| Pull_Request_Opened                 | `pull_request`         | [See the platform file](https://github.com/xatkit-bot-platform/xatkit-github-platform/blob/master/platform/GithubPlatform.platform)       | Event sent when a new pull request is opened in the repository. |
| Pull_Request_Closed                 | `pull_request`         | [See the platform file](https://github.com/xatkit-bot-platform/xatkit-github-platform/blob/master/platform/GithubPlatform.platform)       | Event sent when a pull request is closed.                    |
| Pull_Request_Edited                 | `pull_request`         | [See the platform file](https://github.com/xatkit-bot-platform/xatkit-github-platform/blob/master/platform/GithubPlatform.platform)       | Event sent when a pull request is edited.                    |
| Pull_Request_Reopened               | `pull_request`         | [See the platform file](https://github.com/xatkit-bot-platform/xatkit-github-platform/blob/master/platform/GithubPlatform.platform)       | Event sent when a pull request is reopened.                  |
| Pull_Request_Assigned               | `pull_request`         | [See the platform file](https://github.com/xatkit-bot-platform/xatkit-github-platform/blob/master/platform/GithubPlatform.platform)       | Event sent when a pull request is assigned to a contributor. |
| Pull_Request_Unassigned             | `pull_request`         | [See the platform file](https://github.com/xatkit-bot-platform/xatkit-github-platform/blob/master/platform/GithubPlatform.platform)       | Event sent when a pull request is unassigned from a contributor. |
| Pull_Request_Review_Requested       | `pull_request`         | [See the platform file](https://github.com/xatkit-bot-platform/xatkit-github-platform/blob/master/platform/GithubPlatform.platform)       | Event sent when a review request is requested on a pull request. |
| Pull_Request_Review_Request_Removed | `pull_request`         | [See the platform file](https://github.com/xatkit-bot-platform/xatkit-github-platform/blob/master/platform/GithubPlatform.platform)       | Event sent when a review request on a pull request is removed. |
| Pull_Request_Labeled                | `pull_request`         | [See the platform file](https://github.com/xatkit-bot-platform/xatkit-github-platform/blob/master/platform/GithubPlatform.platform)       | Event sent when a pull request is labeled. **Note**: pull requests that are labeled with multiple labels produce multiple *Pull_Request_Labeled* events. |
| Pull_Request_Unlabeled              | `pull_request`         | [See the platform file](https://github.com/xatkit-bot-platform/xatkit-github-platform/blob/master/platform/GithubPlatform.platform)       | Event sent when a pull request is unlabeled. **Note**: pull requests that are unlabeled from multiple labels produce multiple *Pull_Request_Unlabeled* events. |
| Label_Created                       | `label`                | [See the platform file](https://github.com/xatkit-bot-platform/xatkit-github-platform/blob/master/platform/GithubPlatform.platform)       | Event sent when a new label is created in the repository.    |
| Label_Edited                        | `label`                | [See the platform file](https://github.com/xatkit-bot-platform/xatkit-github-platform/blob/master/platform/GithubPlatform.platform)       | Event sent when a label is edited.                           |
| Label_Deleted                       | `label`                | [See the platform file](https://github.com/xatkit-bot-platform/xatkit-github-platform/blob/master/platform/GithubPlatform.platform)       | Event sent when a label is deleted from the repository.      |
| Push                                | `push`                 | [See the platform file](https://github.com/xatkit-bot-platform/xatkit-github-platform/blob/master/platform/GithubPlatform.platform)       | Event sent when a contributor pushes to the repository. **Note**: push containing multiple commits produce a single *Push* event that contains all the commits' information. |

## Actions

| Action | Parameters                                                   | Return                         | Return Type | Description                                                 |
| ------ | ------------------------------------------------------------ | ------------------------------ | ----------- | ----------------------------------------------------------- |
| OpenIssue | - `user` (**String**): the identifier of the user owning the repository to open an issue in<br />- `repository` (**String**): the name of the repository to open an issue in<br /> - `issueTitle` (**String**): the title of the issue to open<br /> - `issueContent` (**String**): the content of the issue description | The raw API [Issue](http://static.javadoc.io/com.jcabi/jcabi-github/1.0/com/jcabi/github/Issue.html) | [Issue](http://static.javadoc.io/com.jcabi/jcabi-github/1.0/com/jcabi/github/Issue.html) | Opens an issue in the given `user/repository` repository, with the provided `issueTitle` and `issueContent`|
| GetIssue | - `user` (**String**): the identifier of the user owning the repository to retrieve the issue from <br/> - `repository` (**String**): the name of the repository to retrieve the issue from<br/> - `issueNumber` (**String**): the number of the issue to retrieve the issue from | The raw API [Issue](http://static.javadoc.io/com.jcabi/jcabi-github/1.0/com/jcabi/github/Issue.html) | [Issue](http://static.javadoc.io/com.jcabi/jcabi-github/1.0/com/jcabi/github/Issue.html) | Retrieves the issue in the given `user/repository` repository, with the provided `issueNumber` |
| CommentIssue | - `issue` ([**Issue**](http://static.javadoc.io/com.jcabi/jcabi-github/1.0/com/jcabi/github/Issue.html)): the issue to comment (can be retrieved with *OpenIssue* or *GetIssue* actions)<br/> - `comment` (**String**): the content of the comment to post on the issue | The raw API [Comment](https://static.javadoc.io/com.jcabi/jcabi-github/1.0/com/jcabi/github/Comment.html) | [Comment](https://static.javadoc.io/com.jcabi/jcabi-github/1.0/com/jcabi/github/Comment.html) | Posts the provided  `comment` on the given `issue` |
| SetLabel | - `issue` ([**Issue**](http://static.javadoc.io/com.jcabi/jcabi-github/1.0/com/jcabi/github/Issue.html)): the issue to set the label to<br/> - `label` (**String**): the label to set to the `issue` | The label | String | Sets the provided `label` to the given `issue` |
| AssignUser | - `issue` ([**Issue**](http://static.javadoc.io/com.jcabi/jcabi-github/1.0/com/jcabi/github/Issue.html)): the issue to assign an user to<br/> - `username` (**String**): the username of the Github user to assign to the issue | The username of the Github user assigned to the issue | String | Assigns the provided `username` to the given `issue` |

## Options

The Github platform supports the following configuration options

| Key                  | Values | Description                                                  | Constraint    |
| -------------------- | ------ | ------------------------------------------------------------ | ------------- |
| `xatkit.github.username` | String | The username of the Github account used by Xatkit | **Mandatory** if `xatkit.github.oauth.token` is not provided |
| `xatkit.github.password` | String | The password of the Github account used by Xatkit | **Mandatory** if `xatkit.github.oauth.token` is not provided |
| `xatkit.github.oauth.token` | String | The OAuth token of the Github account used by Xatkit | **Mandatory** if `xatkit.github.username` and `xatkit.github.password` are not provided |




