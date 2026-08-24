LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

SRC_URI = "git://git@github.com/cu-ecen-aeld/assignments-3-and-later-PeanutButtermitKase.git;protocol=ssh;branch=master \
           file://aesdchar-init \
"

SRCREV = "faecfaf49cb11c668fdd20d81314f4b793becace"

PV = "1.0+git${SRCPV}"

S = "${WORKDIR}/git"

inherit module
inherit update-rc.d

INITSCRIPT_NAME = "aesdchar-init"
INITSCRIPT_PARAMS = "defaults 98"

EXTRA_OEMAKE += "-C ${STAGING_KERNEL_DIR} M=${S}/aesd-char-driver"

do_install:append() {
    install -d ${D}${sysconfdir}/init.d

    install -m 0755 ${WORKDIR}/aesdchar-init \
        ${D}${sysconfdir}/init.d/aesdchar-init
}

FILES:${PN} += "${sysconfdir}/init.d/aesdchar-init"

RDEPENDS:${PN} += "kernel-module-aesdchar"
