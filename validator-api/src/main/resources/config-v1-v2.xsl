<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="2.0"
                xmlns:v1="http://difi.no/xsd/vefa/validator/1.0"
                xmlns="http://difi.no/xsd/vefa/validator/2.0"
                exclude-result-prefixes="v1">

    <xsl:output method="xml" version="1.0" encoding="UTF-8" indent="no"/>

    <xsl:template match="Definitions">
        <xsl:copy-of select="."/>
    </xsl:template>

    <xsl:template match="v1:buildConfigurations | v1:configurations">
        <Definitions>
            <xsl:if test="@timestamp">
                <xsl:attribute name="timestamp" select="@timestamp"/>
            </xsl:if>
            <xsl:if test="@build">
                <xsl:attribute name="build" select="@build"/>
            </xsl:if>
            <xsl:if test="@name">
                <xsl:attribute name="identifier" select="@name"/>
            </xsl:if>
            <xsl:apply-templates select="v1:package | v1:testfolder | v1:configuration"/>
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
            <xsl:if test="@build">
                <xsl:attribute name="build" select="@build"/>
            </xsl:if>
            <xsl:if test="@weight">
                <xsl:attribute name="weight" select="@weight"/>
            </xsl:if>
            <xsl:apply-templates select="v1:title | v1:customizationId | v1:standardId | v1:declaration | v1:inherit | v1:file | v1:stylesheet | v1:rule"/>
        </DocumentType>
    </xsl:template>

    <xsl:template match="v1:identifier"/>

    <xsl:template match="v1:title">
        <Title>
            <xsl:value-of select="."/>
        </Title>
    </xsl:template>

    <xsl:template match="v1:customizationId">
        <Declaration type="xml.ubl">
            <xsl:value-of select="concat(../v1:profileId, '#', .)"/>
        </Declaration>
    </xsl:template>

    <xsl:template match="v1:profileId"/>

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
        <Inherit required="true">
            <xsl:value-of select="."/>
        </Inherit>
    </xsl:template>

    <xsl:template match="v1:file">
        <Filter>
            <xsl:attribute name="type" select="'xml.schmeatron.xslt'"/>
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

    <xsl:template match="v1:rule">
        <Rule>
            <xsl:attribute name="action" select="@action"/>
            <xsl:attribute name="identifier" select="@identifier"/>
        </Rule>
    </xsl:template>

</xsl:stylesheet>