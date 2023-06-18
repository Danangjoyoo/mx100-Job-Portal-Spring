# MX100 Job Portal

## How to deploy?
1. make sure you have `docker` and `docker compose` installed in your machine
2. make sure your `docker` is running
3. build and serve up
```
docker compose up --build
```
4. API Docs
    - please open https://editor-next.swagger.io/
    - upload `openapi.yaml` on `app/src/main/resources/openapi.yml`
    - then you will see the OpenAPI specs