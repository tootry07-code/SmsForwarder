# 📱 SMS to Email Forwarder — সম্পূর্ণ গাইড

## এই App কী করে?
আপনার ফোনে SMS আসলে স্বয়ংক্রিয়ভাবে Email এ পাঠিয়ে দেবে।
শুধু Internet connection থাকলেই কাজ করবে।

---

## ✅ APK বানানোর ধাপ

### ধাপ ১: Android Studio ডাউনলোড করুন
👉 https://developer.android.com/studio

### ধাপ ২: Project খুলুন
- Android Studio চালু করুন
- "Open" → এই folder টি select করুন: `SmsForwarder/`

### ধাপ ৩: Project Sync করুন
- File → Sync Project with Gradle Files
- কিছুক্ষণ অপেক্ষা করুন (Internet লাগবে)

### ধাপ ৪: APK তৈরি করুন
- Menu: **Build → Build Bundle(s) / APK(s) → Build APK(s)**
- Build শেষ হলে notification আসবে: "locate" এ click করুন
- APK পাবেন: `app/build/outputs/apk/debug/app-debug.apk`

### ধাপ ৫: ফোনে Install করুন
- APK ফাইলটি ফোনে copy করুন
- Settings → Unknown Sources → Allow
- APK তে click করে Install করুন

---

## 📧 Gmail App Password কিভাবে পাবেন

১. Gmail → Google Account → Security
২. **2-Step Verification** চালু করুন
3. **App Passwords** → Select App: "Mail" → Select Device: "Android"
৪. Generate করুন → **16-digit password** পাবেন
৫. এই password টি App এ দিন (regular password না)

---

## 🔧 App Use করার নিয়ম

১. App খুলুন
২. **আপনার Gmail** দিন (যেটা দিয়ে পাঠাবে)
৩. **App Password** দিন (16 digit)
৪. **Forward to Email** দিন (যেখানে SMS যাবে)
৫. **Switch চালু** করুন
৬. **সেভ** করুন

এখন থেকে SMS আসলেই Email পাবেন! ✅

---

## ⚠️ গুরুত্বপূর্ণ তথ্য

- App background এ চলে, ফোন restart এর পরেও কাজ করে
- Android 13+ এ SMS permission manually দিতে হতে পারে
- Settings → Apps → SMS Forwarder → Permissions → SMS ✅
- Battery optimization বন্ধ রাখুন এই app এর জন্য

---

## 📁 File Structure

```
SmsForwarder/
├── app/
│   ├── src/main/
│   │   ├── java/com/smsforwarder/
│   │   │   ├── MainActivity.java      ← UI
│   │   │   ├── SmsReceiver.java       ← SMS ধরে
│   │   │   └── EmailSender.java       ← Email পাঠায়
│   │   ├── res/layout/
│   │   │   └── activity_main.xml      ← Design
│   │   └── AndroidManifest.xml        ← Permissions
│   └── build.gradle                   ← Dependencies
└── GUIDE.md                           ← এই ফাইল
```
