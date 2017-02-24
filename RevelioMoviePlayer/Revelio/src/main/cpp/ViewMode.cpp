//
// Created by Charles on 11/24/16.
//

#include <jni.h>
#include "stdio.h"
#include "unistd.h"
#include "fcntl.h"

int gpio_set_value(jint value) {
    int fd, len;
    char buf[60];

    len = snprintf(buf, sizeof(buf), "/sys/class/i2c-adapter/i2c-2/2-002c/lcos3d");
    fd = open(buf, O_WRONLY);
    if (fd < 0) {
        return fd;
    }

    if (value == 0) // 2D
    {
        write(fd, "0", 1);
    }
    else if (value == 1) // Side by Side
    {
        write(fd, "1", 1);
    }
    else if (value == 3) // Frame by Frame
    {
        write(fd, "3", 1);
    }

    close(fd);

    return 0;
}
