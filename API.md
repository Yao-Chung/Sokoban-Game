# API Document

## Data structures

### Map

A 2D array contains characters

* `#` : Wall
* `@` : Man
* `$` : Box
* `.` : Target (untouched)
* `%` : Target (touched)
* `null`: Empty

```json
[
    ["#", "#", "#", "#"],
    ["#", ".", "%", "#"],
    ["#", "$", null, "#"],
    ["#", "@", null, "#"],
    ["#", "#", "#", "#"]
]
```

## Entries

### Move

#### Request

```curl
GET /move?direction=0
```

#### Response

```js
{
    status: ("win"|"lose"|"continue"),
    map: <Map>
}
```

### Restart

#### Request

```curl
GET /restart
```

#### Response

```
<Map>
```

### Start

#### Request

```curl
GET /start?level=1
```

#### Response

```
<Map>
```

### Levels

#### Request

```curl
GET /levels
```

#### Response

Example:

```json
[
    "level1.txt",
    "level2.txt"
]
```