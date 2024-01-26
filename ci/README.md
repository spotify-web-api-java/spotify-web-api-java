How to update the `codesigning.asc.enc`:

```shell
gpg --export --armor your@email.com > codesigning.asc
gpg --export-secret-keys --armor your@email.com >> codesigning.asc
gpg --output codesigning.asc.enc --symmetric --cipher-algo AES256 codesigning.asc
# protect using $GPG_PASSPHRASE_FILE, not the secret subkey's passphrase
shred --remove codesigning.asc
```
