```shell
gpg --export --armor your@email.com > codesigning.asc
gpg --export-secret-keys --armor your@email.com >> codesigning.asc
travis login --pro --github-token xxxxxxxxxx
travis encrypt-file codesigning.asc
# travis encrypt "GPG_PASSPHRASE=xxxx\\$xxxx"
shred --remove codesigning.asc
```
