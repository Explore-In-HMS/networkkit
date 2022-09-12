# Network Kit Gson Converter

![Latest Version](https://img.shields.io/badge/latestVersion-1.0.0-yellow) ![Kotlin](https://img.shields.io/badge/language-kotlin-blue) ![Minimum SDK Version](https://img.shields.io/badge/minSDK-23-orange)

Network Kit Gson Converter is a ready-to-use converter library that serves as a bridge between the [Gson](https://github.com/google/gson) and [Network Kit](https://developer.huawei.com/consumer/en/hms/huawei-networkkit).  The converter uses GSON to convert Objects into their JSON representation and vice versa.

Usage
-----
In your `RestClient` instance, you can add the Network Kit Gson Converter.
```kotlin
    val restClient: RestClient = RestClient.Builder()
        .baseUrl("https://api.example.com")
        .addConverterFactory(GsonConverterFactory.create(Gson()))
        .build()
```
# Download

Add it to your root build.gradle with:
```gradle
allprojects {
    repositories {
        maven { url "https://jitpack.io" }
    }
}
```
and:

```gradle
dependencies {
    implementation "com.github.Explore-In-HMS.networkkit:converter-gson:1.0.0"
}
```

# License
```xml

 Copyright 2022. Explore in HMS. All rights reserved.

 Licensed under the Apache License, Version 2.0 (the "License");
 you may not use this file except in compliance with the License.
 You may obtain a copy of the License at

 http://www.apache.org/licenses/LICENSE-2.0

 Unless required by applicable law or agreed to in writing, software
 distributed under the License is distributed on an "AS IS" BASIS,
 WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 See the License for the specific language governing permissions and
 limitations under the License.

```