LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

SRC_URI = "git://git@github.com/cu-ecen-aeld/assignments-3-and-later-PeanutButtermitKase.git;protocol=ssh;branch=master"

PV = "1.0+git${SRCPV}"

SRCREV = "faecfaf49cb11c668fdd20d81314f4b793becace"

S = "${WORKDIR}/git/server"

inherit update-rc.d

INITSCRIPT_NAME = "aesdsocket"
INITSCRIPT_PARAMS = "defaults 99"

FILES:${PN} += "${bindir}/aesdsocket"
FILES:${PN} += "${sysconfdir}/init.d/aesdsocket"

do_configure () {
    :
}

do_compile () {
    oe_runmake \
        CC="${CC}" \
        USE_AESD_CHAR_DEVICE=1 \
        CFLAGS="${CFLAGS} ${LDFLAGS} -pthread -DUSE_AESD_CHAR_DEVICE=1"
}

do_install () {
    install -d ${D}${bindir}
    install -m 0755 ${S}/aesdsocket \
        ${D}${bindir}/aesdsocket

    install -d ${D}${sysconfdir}/init.d
    install -m 0755 ${S}/aesdsocket-start-stop \
        ${D}${sysconfdir}/init.d/aesdsocket
}