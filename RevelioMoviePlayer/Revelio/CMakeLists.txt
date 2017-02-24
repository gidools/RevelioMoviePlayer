cmake_minimum_required(VERSION 3.4.1)
add_library( revelio
             SHARED
             src/main/cpp/NativeManager.cpp
             src/main/cpp/ViewMode.cpp)

find_library( log-lib log )
target_link_libraries( revelio ${log-lib} )