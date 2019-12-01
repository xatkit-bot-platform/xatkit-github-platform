# Changelog

All notable changes for the Github platform will be documented in this file.

Note that there is no changelog available for the initial release of the platform (2.0.0), you can find the release notes [here](https://github.com/xatkit-bot-platform/xatkit-github-platform/releases).

The changelog format is based on [Keep a Changelog](https://keepachangelog.com/en/1.0.0/), and this project adheres to [Semantic Versioning](https://semver.org/v2.0.0.html)

## Unreleased

### Changed
- Action parameters and return are now statically typed. **This change breaks the public API**: execution models relying on the generic `Object` type for parameter and return now need to cast values to the expected type. (e.g. `ChatPlatform.Reply(message)` now requires that `message` is a `String`, this can be fixed with the following syntax `ChatPlatform.Reply(message as String)`).  

## [2.1.0]- 2019-10-10

### Changed

- `GithubWebhookEventProvider` now uses the new intent provider hierarchy (see [xatkit-runtime/#221](https://github.com/xatkit-bot-platform/xatkit-runtime/issues/221)). This doesn't change the public API, but requires to use the latest version of [xatkit-runtime](https://github.com/xatkit-bot-platform/xatkit-runtime).
- Headers are now matched in a case insensitive way (see [this commit on xatkit-runtime](https://github.com/xatkit-bot-platform/xatkit-runtime/commit/8217a74296cd9cee2a774204106a11dca766a29d)).

## [2.0.0] - 2019-08-20 

See the release notes [here](https://github.com/xatkit-bot-platform/xatkit-github-platform/releases).

