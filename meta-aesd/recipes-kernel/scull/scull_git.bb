LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

SRC_URI = "git://git@github.com/cu-ecen-aeld/assignment-7-PeanutButtermitKase.git;protocol=ssh;branch=main \
           file://scull-init \
"

SRCREV = "28f853992a85f3397e66ce2ce93e5e4ea60c003a"

PV = "1.0+git${SRCPV}"

S = "${WORKDIR}/git"

inherit module
inherit update-rc.d

INITSCRIPT_NAME = "scull-init"
INITSCRIPT_PARAMS = "defaults 98"

EXTRA_OEMAKE += "-C ${STAGING_KERNEL_DIR} M=${S}/scull"

do_install:append() {
    install -d ${D}${sysconfdir}/init.d
    install -m 0755 ${WORKDIR}/scull-init \
        ${D}${sysconfdir}/init.d/scull-init
}

FILES:${PN} += "${sysconfdir}/init.d/scull-init"

RDEPENDS:${PN} += "kernel-module-scull"