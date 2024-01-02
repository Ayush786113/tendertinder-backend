
# Tender Tinder

In the second half of 2023, a friend of mine insisted me to open a Tinder account. At first I was skeptical but then It turned out well. No, I didn't get any date there but interestingly, I started enumerating and learning the API of Tinder. After intensive enumeration, I found the endpoints and necessary headers to access recommendations and other unintended ways of using Tinder. So I created kind of reverse API for Tinder and dumped the accounts of as many accounts as possible (all public data) into my own database.

No frontend is prepared yet but will love some contributuion.
## Demo

The deployed API can be found [here](https://tendertinder.onrender.com/)
## Tech Stack

   - Java Spring Boot
   - Appwrite
   - Tinder API
## API Reference

#### Get all dumped accounts
(25 accounts are returned by default, paginated response will be implemented in future)

```http
  GET /
```

#### Search accounts by name

```http
  GET /name=[name]
```

| Parameter | Type     | Description                       |
| :-------- | :------- | :-------------------------------- |
| `name`      | `string` |  search by name |

#### Search accounts by gender

```http
  GET /gender=[gender]
```

| Parameter | Type     | Description                       |
| :-------- | :------- | :-------------------------------- |
| `gender`      | `string` |  search by gender (male/female) |

#### Search accounts by city

```http
  GET /city=[city]
```

| Parameter | Type     | Description                       |
| :-------- | :------- | :-------------------------------- |
| `city`      | `string` |  search by city |

#### Get recommendations tailored for you by Tinder

```http
  GET /recs?token=[X-Auth-Token]
```

| Parameter | Type     | Description                       |
| :-------- | :------- | :-------------------------------- |
| `X-Auth-Token`      | `string` | **Required**. API token of the user |

#### Get the latest 20 accounts you've liked in Tinder

```http
  GET /likes?token=[X-Auth-Token]
```

| Parameter | Type     | Description                       |
| :-------- | :------- | :-------------------------------- |
| `X-Auth-Token`      | `string` | **Required**. API token of the user |

## Features

- No need for endless swiping, get all possible Tinder accounts at once or in pages
- See all the recommendations at once in the form of a table
- See the last few people you've liked
## FAQ

#### How to use the API?

The API currently returns only 25 responses for sanity of the browser and the user. There are 1000+ accounts stored and are added daily. Just a simple 'GET' request will fetch you data.

#### How can I get my X-Auth-Token?

Login to Tinder from a desktop browser and open the developer tools. In the storage section, check in the local storage, a field name "TinderWeb/APIToken" contains a UUID4. That is your API token.

#### The token that worked previously is not working now. What to do?

Tinder refreshes tokens at regular intervals or after you re-login. Just use the new token and the API will be functional again.

#### Why did you I make this crazy thing in the first place?

To demonstrate my backend skills and have a working proof of the same to include in CV. Have to bag a job in 2024 :)

#### Are you still single even after enumerating and playing with Tinder?

Yes, I am still single.
## Acknowledgements

[All Idea came from this article](https://blog.securityevaluators.com/reverse-engineering-bumbles-api-a2a0d39b3a87)
## Contributing

Contributions are always welcome!

Anyone can contribute to this repository by creating a UI or some interesting improvements in the API.

I cannot assure to accept all the contributions but I'll try to manage as many as possible.
## Feedback

If you have any feedback, please reach out to me at ayush786113@gmail.com

## Authors
[Ayush](https://www.github.com/ayush786113)