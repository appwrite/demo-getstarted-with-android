# 🤖 Get Started With Android 

A Simple Android App to get started with Appwrite's Android SDK

## 🚀 Usage

- Clone the repository

```sh
git clone https://github.com/appwrite/demo-getstarted-with-android
```

- Import into Android Studio and run!

## 📁 Folder Structure

Each folder under the `ui` directory has interactions with one particular API of Appwrite.

The `Accounts` directory contains interactions with Appwrite's Account's API and so on.

`Client.kt` contains a singleton object that initializes the Appwrite client.

`Config.kt` contains a singleton object that contains the Appwrite project's configuration.

```sh
➜  appwrite-android-demo git:(main) ✗ tree app/src/main/java/com/example/appwritedemoapplication/
app/src/main/java/com/example/appwritedemoapplication/
├── MainActivity.kt
├── Config.kt
├── ui
│   ├── Accounts
│   │   ├── AccountsFragment.kt
│   │   └── AccountsViewModel.kt
│   ├── Avatars
│   │   ├── AvatarsFragment.kt
│   │   └── AvatarsViewModel.kt
│   ├── Database
│   │   ├── DatabaseFragment.kt
│   │   └── DatabaseViewModel.kt
│   ├── Functions
│   │   ├── FunctionsFragment.kt
│   │   └── FunctionsViewModel.kt
│   ├── Localization
│   │   ├── LocalizationFragment.kt
│   │   └── LocalizationViewModel.kt
│   ├── Storage
│   │   ├── StorageFragment.kt
│   │   └── StorageViewModel.kt
│   └── Teams
│       ├── TeamsFragment.kt
│       └── TeamsViewModel.kt
└── utils
    ├── Client.kt
    └── Event.kt

```

## ✨ Screenshots

<p align="center">
   <img src="https://user-images.githubusercontent.com/20852629/121349702-7358ea00-c947-11eb-88f1-0c520647fc8e.png" width="300">
  <img src="https://user-images.githubusercontent.com/20852629/121349551-47d5ff80-c947-11eb-8fdd-1f8e8bb47726.png" width="300">
  <img src="https://user-images.githubusercontent.com/20852629/121349692-705df980-c947-11eb-8222-4539e4e0e60e.png" width="300">
 
</p>

## 🍻 Contributing

Contributions, issues and feature requests are welcome.<br />
Feel free to check [issues page](https://github.com/appwrite/demo-getstarted-with-android/issues) if you want to contribute.


## 🤕 Support 
If you get stuck anywhere, hop onto one of our [support channels in discord](https://appwrite.io/discord) and we'd be delighted to help you out 🤝
