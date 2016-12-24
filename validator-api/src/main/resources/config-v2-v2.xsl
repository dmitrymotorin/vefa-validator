<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="2.0"
                xmlns="http://difi.no/xsd/vefa/validator/2.0">

    <xsl:output method="xml" version="1.0" encoding="UTF-8" indent="no"/>

    <xsl:template match="Definitions">
        <xsl:copy-of select="."/>
    </xsl:template>

</xsl:stylesheet>