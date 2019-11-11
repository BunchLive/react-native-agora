
## Running the example

1. Copy the `debug.keystore` file to `./android/app/` folder.

	```
	$ cp ~/.android/debug.keystore example/android/app/
	```

2. Create `./agora_app_id.json` file with your Agora APP_ID:

    ```json
    {
      "APP_ID": "REPLACE WITH AGORA APP ID"
    }
    ```

3. Install all dependencies.

	```
	$ cd example
	$ npm install
	```

4. Connect your android device and use react-native cli to run the app

	```
	$ react-native run-android
	```
