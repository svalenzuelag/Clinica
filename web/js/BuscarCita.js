
function doSearch(){
    var tableReg = document.getElementById('tblCita');
    var searchText = document.getElementById('txt_Buscar').value.toLowerCase();
    var cellsOfRow = "";
    var found = false;
    var compareWith = "";
    
    for(var i = 1; i<tableReg.rows.length; i++)
    {
        cellsOfRow = tableReg.rows[i].getElementsByTagName('td');
        found = false;
        
        for(var j = 0; j< cellsOfRow.length && !found; j++)
        {
            compareWith = cellsOfRow[j].innerHTML.toLowerCase();
            if( searchText.length == 0|| (compareWith.indexOf(searchText) > -1))
            {
                found = true;
            }
        }
        if(found)
        {
            tableReg.rows[i].style.display = '';
        }else
        {
        tableReg.rows[i].style.display = 'none'; 
        }
    }
}