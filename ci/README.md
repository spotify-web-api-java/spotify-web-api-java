```shell
gpg --export --armor your@email.com > codesigning.asc
gpg --export-secret-keys --armor your@email.com >> codesigning.asc
gpg --output codesigning.asc.enc --symmetric --cipher-algo AES256 codesigning.asc
shred --remove codesigning.asc
```
