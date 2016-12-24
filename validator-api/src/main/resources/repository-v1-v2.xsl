<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="2.0"
                xmlns:v1="http://difi.no/xsd/vefa/validator/1.0"
                xmlns="http://difi.no/xsd/vefa/validator/repository/2.0"
                exclude-result-prefixes="v1">

    <xsl:output method="xml" version="1.0" encoding="UTF-8" indent="no"/>

    <xsl:template match="Repository">
        <xsl:copy-of select="."/>
    </xsl:template>

    <xsl:template match="v1:artifacts">
        <Repository>
            <xsl:attribute name="updated" select="@timestamp"/>

            <xsl:apply-templates select="v1:artifact"/>
        </Repository>
    </xsl:template>

    <xsl:template match="v1:artifact">
        <Package>
            <xsl:attribute name="name" select="@name"/>
            <xsl:attribute name="timestamp" select="@timestamp"/>
            <xsl:attribute name="filename" select="@filename"/>
        </Package>
    </xsl:template>

</xsl:stylesheet>