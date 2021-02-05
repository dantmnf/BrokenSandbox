# BrokenSandbox

TL;DR;

* Android 10 introduced `ZygotePreload` for services using isolated process.
* The process running app `ZygotePreload` code (app_zygote) is allowed to call `setresuid` and `setresgid`.
* A seccomp filter is installed to enforce availiable UID/GID range of isolated processes forked from app_zygote, *but only if SELinux is enforcing*. [more](https://cs.android.com/android/platform/superproject/+/android-11.0.0_r3:frameworks/base/core/jni/com_android_internal_os_Zygote.cpp;l=634;bpv=1;bpt=1)

P.S. This is [part of CTS](https://cs.android.com/android/platform/superproject/+/android-11.0.0_r3:cts/hostsidetests/seccomp/app/src/android/seccomp/cts/app/ZygotePreload.java;bpv=1;bpt=0).
