inherit gettext

SUMMARY = "Downloads the Asus Zenwatch 3 /system and /usr/include/android folders and installs them for libhybris"
LICENSE = "CLOSED"
#SRC_URI = "http://kicherer.org/aos/system.tar.gz"
SRC_URI = "file://system-MWG68.tar.gz"
SRC_URI[md5sum] = "8e454cfbc0134f4781e8e4f2ab4452d5"
SRC_URI[sha256sum] = "8937d233fc1911373a2e7cc1962a3e6bdb6c88a43be480a8ec98a2e10e422911"
#SRC_URI[md5sum] = "d96f7b170853dec0b909c069e8d56ca8"
#SRC_URI[sha256sum] = "875b4f87dd430898204b4b7ebaf65fe6e298e205e21a18156873b6817cc60340"
#SRC_URI[md5sum] = "1edfb5421bee4c612089c2a7ccfb820b"
#SRC_URI[sha256sum] = "7d7ebe3be1711743ace11ed63aa9181213fb3437477efb499d2457c033a07748"
#SRC_URI[md5sum] = "90d9325bc9b5a57f13acb013b2833804"
#SRC_URI[sha256sum] = "20e19062d0d6079358f587e5602f15fdfeed410ff5460fb93d2e3ce10a869840"
#SRC_URI[md5sum] = "0c01844e55558252f2dd1cc7614e355c"
#SRC_URI[sha256sum] = "586b4b2641eba16924caebb483dfeb5f9a126c4ca91732030a6a6b17eb3cafdf"
#SRC_URI[md5sum] = "d191b9574be1d7e06dc1594d82b579bf"
#SRC_URI[sha256sum] = "df46fdec7e8e67a12f1b5013ab2bf2552dce958be742c0fed207db99e2b71353"
#SRC_URI[md5sum] = "60ab67b7b988ee018258744efc547cb7"
#SRC_URI[sha256sum] = "fbe6f939e7062bb14f0df4698dddd9c31e1a098ddb3cceecaee18ddb2fc43366"
PV = "marshmallow"

PACKAGE_ARCH = "${MACHINE_ARCH}"
INHIBIT_PACKAGE_STRIP = "1"
COMPATIBLE_MACHINE = "swift"
INSANE_SKIP_${PN} = "already-stripped"
S = "${WORKDIR}"
B = "${S}"

PROVIDES += "virtual/android-system-image"
PROVIDES += "virtual/android-headers"

do_install() {
    install -d ${D}/system/
    cp -r system/* ${D}/system/

    sed -i -e '$aro.qc.bluetooth.stack=bluez' ${D}/system/build.prop

    install -d ${D}/usr/
    cp -r usr/* ${D}/usr/

    install -d ${D}${includedir}/android
    cp -r include/* ${D}${includedir}/android/

    install -d ${D}${libdir}/pkgconfig
    install -m 0644 ${D}${includedir}/android/android-headers.pc ${D}${libdir}/pkgconfig
    rm ${D}${includedir}/android/android-headers.pc
    cd ${D}
    ln -s system/vendor vendor
}

# FIXME: QA Issue: Architecture did not match (40 to 164) on /work/dory-oe-linux-gnueabi/android/lollipop-r0/packages-split/android-system/system/vendor/firmware/adsp.b00 [arch]
do_package_qa() {
}

PACKAGES =+ "android-system android-headers"
FILES_android-system = "/system /vendor /usr"
FILES_android-headers = "${libdir}/pkgconfig ${includedir}/android"
EXCLUDE_FROM_SHLIBS = "1"
