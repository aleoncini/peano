function start() {
    var code = $("#txtCode").val();
    importRound(code, formatSuccessfullImportResponse, formatBadImportResponse);
};