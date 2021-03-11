#FILESEXTRAPATHS_prepend_swift := "${THISDIR}/qt5-qpa-hwcomposer-plugin:"
#    file://0001-Adapt-to-what-tetra-s-hwcomposer-expects.patch;striplevel=2 

SRC_URI_append_swift = " \
    file://0002-Add-QCOM_BSP-define-switch.patch;striplevel=2 \
    file://004-Includes-sync.h-which-provides-sync_wait.patch;striplevel=2 \
"
