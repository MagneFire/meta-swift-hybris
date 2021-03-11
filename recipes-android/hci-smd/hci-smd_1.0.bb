DESCRIPTION = "Load the WiFi firmware and enable driver"
PR = "r0"
SRC_URI = "file://hci-smd.service"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/Apache-2.0;md5=89aea4e17d99a7cacdbeed46a0096b10"
S = "${WORKDIR}"

PACKAGE_ARCH = "${MACHINE_ARCH}"

do_install() {
    install -d ${D}/lib/systemd/system/multi-user.target.wants/
    cp ${WORKDIR}/hci-smd.service ${D}/lib/systemd/system/
    ln -s ../hci-smd.service ${D}/lib/systemd/system/multi-user.target.wants/hci-smd.service
}

FILES_${PN} += "/lib/systemd/system/"
