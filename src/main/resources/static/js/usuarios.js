// Call the dataTables jQuery plugin
$(document).ready(function() {
    cargarUsuarios()
  $('#usuarios').DataTable();
});

async function cargarUsuarios(){

    const request = await fetch('http://localhost:8080/api/usuarios', {
        method: 'GET',
        headers: getHeaders(),
      });
      const usuarios = await request.json();




      let listadoHtml = '';
      for (let usuario  of usuarios){
      let telefonoTel = usuario.telefono == null ? '-' : usuario.telefono;
      let botonEliminar = '<a href="#" onclick="eliminarUsuario('+usuario.id+')" class="btn btn-danger btn-circle"><i class="fas fa-trash"></i></a>';

      let usuarioHtml = '<tr><td>'+usuario.id+'</td><td>'+usuario.nombre+' '+usuario.apellido+' </td><td>'+usuario.email+'</td><td>'+telefonoTel+'</td><td>'+botonEliminar+'</td></tr>';
      listadoHtml += usuarioHtml;
      }


      document.querySelector("#usuarios tbody").outerHTML=listadoHtml;
}

function getHeaders(){
return {
                     'Accept': 'application/json',
                     'Content-Type': 'application/json',
                     'Authorization': localStorage.token
                   };
}

async function eliminarUsuario(id){

    if(!confirm('¿Deseas eliminar?')){
        return;
    }

    const request = await fetch('http://localhost:8080/api/usuarios/'+ id, {
            method: 'DELETE',
            headers: getHeaders(),
          });
    location.reload();
}