# For more information about using CMake with Android Studio, read the
# documentation: https://d.android.com/studio/projects/add-native-code.html

# Sets the minimum version of CMake required to build the native library.

cmake_minimum_required(VERSION 3.10.2)#最低支持Cmake版本
#todo 第一步导入头文件
include("inc")

# Declares and names the project.

project("qqfmod")

# Creates and names a library, sets it as either STATIC
# or SHARED, and provides the relative paths to its source code.
# You can define multiple libraries, and CMake builds them for you.
# Gradle automatically packages shared libraries with your APK.
#批量导入所以源文件
add_library( # Sets the name of the library.
        native-lib

        # Sets the library as a shared library.
        SHARED#动态库
        ${allCPP}

        # Provides a relative path to your source file(s).
        native-lib.cpp)

# Searches for a specified prebuilt library and stores the path as a
# variable. Because CMake includes system libraries in the search path by
# default, you only need to specify the name of the public NDK library
# you want to add. CMake verifies that the library exists before
# completing its build.
#第二步：导入库文件
set(CMAKE_CXX_FLAGS "${CMAKE_CXX_FLAGS}-L${CMAKE_SOURCE_DIR}/../jniLibs/${CMAKE_ANDROID_ARCH_ABI}")
find_library( # Sets the name of the path variable.
        log-lib

        # Specifies the name of the NDK library that
        # you want CMake to locate.
        log)

# Specifies libraries CMake should link to your target library. You
# can link multiple libraries, such as libraries you define in this
# build script, prebuilt third-party libraries, or system libraries.
#第三部链接具体的库到我们的libnative-lib。so总库
target_link_libraries( # Specifies the target library.
        native-lib
        log #自动寻找
        fmod
        fmodl#具体的库链接到libnative-lib.so里面去
        # Links the target library to the log library
        # included in the NDK.
        ${log-lib})