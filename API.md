# API Document

## Data structures

### Map

TODO

## Entries

### Move

#### Request

```curl
GET /move?direction=0
```

#### Response

```json
{
    status: ("win"|"lose"|"continue"),
    map: <map>
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

```json
[
    "level1",
    "level2",
    ...
]
```