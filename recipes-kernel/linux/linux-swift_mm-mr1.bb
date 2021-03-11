require recipes-kernel/linux/linux.inc
inherit gettext

SECTION = "kernel"
SUMMARY = "Android kernel for the Asus Zenwatch 3"
HOMEPAGE = "https://android.googlesource.com/"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=d7810fab7487fb0aad327b76f1be7cd7"
COMPATIBLE_MACHINE = "swift"

#    file://0001-gadget-u_ether-Fix-missing-include.patch 
#    file://0001-configfs-Add-ASUS-function-for-USB-connection-state.patch 
SRC_URI = "git://android.googlesource.com/kernel/msm;branch=android-msm-swift-3.18-marshmallow-mr1-wear-release;protocol=https \
    file://defconfig \
    file://img_info \
    file://0001-scripts-dtc-Remove-redundant-YYLOC-global-declaratio.patch \
    file://0003-ARM-uaccess-remove-put_user-code-duplication.patch \
    file://0004-including-backports-from-4.1.48.patch \
    file://0005-backport-HCI-SMD-to-backported-bluetooth-stack.patch \
    file://0001-touchscreen-Disable-wakeup-detection-and-palm-report.patch"

SRCREV = "2f958570bcf7457da4827dc8da5ff3195d447cb3"
# Fail (head)
#SRCREV = "63708089e87e2f5964304f82cf0d36638df03615"
# Same
#SRCREV = "a8e42e6af168fd6fab43172e10680ff483dfd470"
# Same
#SRCREV = "873ceddafe5ab2ab3fbcb628b17e2f8c060d6a38"
#SRCREV = "c718032d21fb6d1d524cee8789d8d68a149497ce"
# Broken?
#SRCREV = "6fe2a15f938521e0d564f9300eaeba51eb5767b3"
# Broken?
#SRCREV = "860f818fef29fa2a0fcbec3da7535fe2a8c8bcc3"

# Broken
#SRCREV = "b85a4f467d65c2296b64ab90ebdbf6d86fc21e44"
# Broken
#SRCREV = "1988d0d3fadd6550fa68a44389ba07511e30cc4f"

# Not working (Garbled)
#SRCREV = "28459e333adc79cbcd7f56867b5b3c1985801f33"
# Garbled
#SRCREV = "3c769486e66787c8fcf002224b083e3d67bb96cb"
# Garbled
#SRCREV = "8945825d9691649b9faafeb986327107905170f3"
# Garbled
#SRCREV = "487e798aa17950adb1a4ccd7db32ecf04235f5ce"
# Garbled
#SRCREV = "7652dec1341e1fc5402aaa7e614c3024993121b8"
# Garbled
#SRCREV = "1f64bb21550e491c4e6625b3b131e5163ad65a52"


KERNEL_MODULE_AUTOLOAD += "hci_smd"


LINUX_VERSION ?= "3.18"
PV = "${LINUX_VERSION}+marshmallow"
S = "${WORKDIR}/git"
B = "${S}"

do_configure_prepend() {
    sed -i "s/ASUS_SW_VER/\"aos1\"/" ${S}/kernel/asusevtlog.c
}

do_install_append() {
    rm -rf ${D}/usr/src/usr/
}

BOOT_PARTITION = "/dev/sda"

inherit mkboot old-kernel-gcc-hdrs
