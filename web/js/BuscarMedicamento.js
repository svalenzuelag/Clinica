function doSearch(){
    var divReg,i;
    divReg = [""]; 
    document.getElementById('divMedicamento').innerHTML = divReg;
    var searchText = document.getElementById('txt_Buscar').value.toLowerCase();
    
    var found = false;
    var compareWith = "";
    
        for(i= 0; i< divReg.length && !found; i++)
        {
            compareWith = divReg[i].innerHTML.toLowerCase();
            if( searchText.length == 0|| (compareWith.indexOf(searchText) > -1))
            {
                found = true;
            }
        }
        if(found)
        {
            divReg.rows[i].style.display = '';
        }else
        {
        divReg.rows[i].style.display = 'none'; 
        }
    
}