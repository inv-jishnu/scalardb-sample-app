# Scalar DB Sample App

This is a small sample application for Scalar DB named contact management.

The commands to run the application using gradle is provided below:

## Using transaction service

Create schema using [email_transaction.json](schema/email_transaction.json).

### To add contact 

```console
$ ./gradlew run --args="-mode transaction -action add -name <name> -email <email_id> -phone <phone_no>"
```

### To get contact

```console
$ ./gradlew run --args="-mode transaction -action get -name <name> -email <email_id>"
```

### To get all contacts

```console
$ ./gradlew run --args="-mode transaction -action scan -name <name>"
```

## Using storage service

Create schema using [email.json](schema/email.json).

### To add contact 

```console
$ ./gradlew run --args="-mode storage -action add -name <name> -email <email_id> -phone <phone_no>"
```

### To get contact

```console
$ ./gradlew run --args="-mode storage -action get -name <name> -email <email_id>"
```

### To get all contacts

```console
$ ./gradlew run --args="-mode storage -action scan -name <name>"
```
