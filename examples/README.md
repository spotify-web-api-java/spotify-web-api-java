# Examples
Here you can find example code snippets.

## Default Data
The following (random) data should be used when modifying examples:

### Authorization
- Client ID: `"zyuxhfo1c51b5hxjk09x2uhv5n0svgd6g"`
- Client secret: `"zudknyqbh3wunbhcvg9uyvo7uwzeu6nne"`
- Redirect URI: `SpotifyHttpManager.makeUri("https://example.com/spotify-redirect")`
- Authorization code: `"c-oGaPdYJF3tu3oUZRUiBHWQvm4oHnBrsxfHackYzzomKJiy5te1k04LJdr6XxjACe9TonpJR8NPOQ3o5btASx_oMw4trmXLYdkda77wY0NJ9Scl69lKvGiOfdnRi5Q0IbBu185Y0TZgyUJz3Auqqv-Wk7zjRke4DzqYEc3ucyUBOq08j5223te-G2K72aL9PxgVJaEHBbLvhdJscCy-zcyU29EZoNlG_E5"`
- Refresh token: `"b0KuPuLw77Z0hQhCsK-GTHoEx_kethtn357V7iqwEpCTIsLgqbBC_vQBTGC6M5rINl0FrqHK-D3cbOsMOlfyVKuQPvpyGcLcxAoLOTpYXc28nVwB7iBq2oKj9G9lHkFOUKn"`
- Access token: `"taHZ2SdB-bPA3FsK3D7ZN5npZS47cMy-IEySVEGttOhXmqaVAIo0ESvTCLjLBifhHOHOIuhFUKPW1WMDP7w6dj3MAZdWT8CLI2MkZaXbYLTeoDvXesf2eeiLYPBGdx8tIwQJKgV8XdnzH_DONk"`

### IDs
- Artist: ["0LcJLqbBmaGUft1e9Mm8HV"](https://open.spotify.com/artist/0LcJLqbBmaGUft1e9Mm8HV?si=tUHw6JYdQwmE5QIla_7ZaQ)
- Album: ["5zT1JLIj9E57p3e1rFm9Uq"](https://open.spotify.com/album/5zT1JLIj9E57p3e1rFm9Uq?si=PkAs2KWQTkm2b0vxg_qcRA)
- Playlist: ["3AGOiaoRXMSjswCLtuNqv5"](https://open.spotify.com/user/abbaspotify/playlist/3AGOiaoRXMSjswCLtuNqv5?si=ru1yCc8QSueG7gHEC7E40w)
- Track: ["01iyCAUm8EvOFqVWYJ3dVX"](https://open.spotify.com/track/01iyCAUm8EvOFqVWYJ3dVX?si=m0Fdh5ASScyfue6kQerIZg)
- User: ["abbaspotify"](https://open.spotify.com/user/abbaspotify?si=xlmKbz6mQZ6fLfqwtOGozg)

### Required
- Category ID: `"dinner"`
- Country: `CountryCode.SE`
- Ids: see appropriate IDs
- Insert before: `0`
- Model object type: first appropriate
- Position ms: `10000`
- Public: `false`
- Q: `"Abba"`
- Range start: `0`
- State: `"track"` or `true`
- Type: `ModelObjectType.ARTIST`
- Volume percent: `100`

### Optional
- After: see appropriate IDs or `new Date(1517087230000L)`
- Album type: `AlbumType.ALBUM.getType()`
- Before: `new Date(1453932420000L)`
- Collaborative: `false`
- Context URI: `"spotify:album:5zT1JLIj9E57p3e1rFm9Uq"`
- Description: `"Amazing music."`
- Device ID: `"5fbb3ba6aa454b5534c4ba43a8c7e8e45a63ad0e"`
- Fields: `"description"`
- Image data: see `image_data.txt`
- Limit: `10`
- Locale: `LanguageCode.sv + "_" + CountryCode.SE`
- Market: `CountryCode.SE`
- Max popularity: `50`
- Min popularity: `10`
- Name: `"Abba"`
- Offset: `0` or `new JsonParser().parse("{\"uri\":\"spotify:track:01iyCAUm8EvOFqVWYJ3dVX\"}").getAsJsonObject()`
- Play: `false`
- Position: `0`
- Public: `false`
- Range length: `1`
- Scope: `"user-read-birthdate,user-read-email"`
- Seed artists: `"0LcJLqbBmaGUft1e9Mm8HV"`
- Seed genres: `"electro"`
- Seed tracks: `"01iyCAUm8EvOFqVWYJ3dVX"`
- Show dialog: `true`
- Snapshot ID: `"JbtmHBDBAYu3/bt8BOXKjzKx3i0b6LCa/wVjyl6qQ2Yf6nFXkbmzuEa+ZI/U1yF+"`
- State: `"x4xkmn9pu3j6ukrs8n"`
- Target popularity: `20`
- Time range: `"medium_term"`
- Timestamp: `new Date(1414054800000L)`
- Tracks: `new JsonParser().parse("[\"01iyCAUm8EvOFqVWYJ3dVX\"]").getAsJsonArray()`
- URIs: `new JsonParser().parse("{\"spotify:track:01iyCAUm8EvOFqVWYJ3dVX\"}").getAsJsonArray()`