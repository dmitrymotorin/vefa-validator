<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="2.0"
                xmlns:v1="http://difi.no/xsd/vefa/validator/1.0"
                xmlns="http://difi.no/xsd/vefa/validator/2.0"
                exclude-result-prefixes="v1">

    <xsl:output method="xml" version="1.0" encoding="UTF-8" indent="no"/>

    <xsl:template match="Definitions">
        <xsl:copy-of select="."/>
    </xsl:template>

    <xsl:template match="v1:buildConfigurations">
        <Definitions>
            <xsl:apply-templates/>
        </Definitions>
    </xsl:template>

    <xsl:template match="v1:package">
        <Package>
            <xsl:if test="@url">
                <xsl:attribute name="url" select="@url"/>
            </xsl:if>
            <xsl:value-of select="."/>
        </Package>
    </xsl:template>

    <xsl:template match="v1:testfolder">
        <TestFolder>
            <xsl:value-of select="."/>
        </TestFolder>
    </xsl:template>

    <xsl:template match="v1:configuration">
        <DocumentType>
            <xsl:attribute name="identifier" select="v1:identifier"/>
            <xsl:apply-templates/>
        </DocumentType>
    </xsl:template>

    <xsl:template match="v1:identifier">
        <!-- No action -->
    </xsl:template>

    <xsl:template match="v1:title">
        <Title>
            <xsl:value-of select="."/>
        </Title>
    </xsl:template>

    <xsl:template match="v1:standardId">
        <Declaration>
            <xsl:value-of select="."/>
        </Declaration>
    </xsl:template>

    <xsl:template match="v1:declaration">
        <Declaration>
            <xsl:attribute name="type" select="@type"/>
            <xsl:value-of select="."/>
        </Declaration>
    </xsl:template>

    <xsl:template match="v1:inherit">
        <Inherit>
            <xsl:value-of select="."/>
        </Inherit>
    </xsl:template>

    <xsl:template match="v1:file">
        <Filter>
            <xsl:attribute name="type" select="'xml.schmeatron'"/>
            <xsl:attribute name="file" select="@path"/>
            <xsl:if test="@source">
                <xsl:attribute name="source" select="@source"/>
            </xsl:if>
        </Filter>
    </xsl:template>

    <xsl:template match="v1:stylesheet">
        <Stylesheet>
            <xsl:attribute name="type" select="'xml.xslt'"/>
            <xsl:value-of select="."/>
        </Stylesheet>
    </xsl:template>

</xsl:stylesheet>