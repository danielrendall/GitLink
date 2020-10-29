<h1 align="center">GitLink</h1>

<p align="center">
    <strong>A Jetbrains plugin that provides shortcuts to open a file or commit in GitHub, Bitbucket, GitLab, Gitea,
    Gogs or GitBlit using the default browser.</strong>
</p>

<p>Forked from <a href="https://github.com/ben-gibson/GitLink">ben-gibson / GitLink</a> with apologies for
my terrible hacking. Consult the README there for build / installation instructions.</p>

Reason for forking
-------------------------------------------------------------------------------

I wanted an easy way to generate links to a Github file on the master branch rather than a specific commit (for 
documentation purposes) so I added some suitable menu options. I know nothing about IntelliJ plugin development, but I
know how to cut and paste, and hack things about.

I also changed the way the link generation works so that if there's no line number selection (e.g. you right-click on
a file in the tree and choose to generate link to branch), you don't get the annoying "#L0-L0" bit in the link. This
is done in an ad-hoc way - if there's no line number selection, we check if the link template has a "#" in it and strip
it off if so on the assumption that it's a line number fragment. Crude, but effective.

License
-------------------------------------------------------------------------------

Please see [LICENSE](LICENSE) for details.
