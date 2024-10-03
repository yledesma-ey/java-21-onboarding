const express = require('express');
const app = express();

// Servicio 1
app.get('/service/worldsys', (req, res) => {
   const dni =  req.query.dni

   let data = [
    {dni:12345678,isTerrotist:true},
    {dni:12345675,isTerrotist:false},
    {dni:12345674,isTerrotist:true},
    {dni:12345673,isTerrotist:false},
    {dni:12345672,isTerrotist:true},
    {dni:30123456,isTerrotist:false},


]


    res.status(200).json({response:data.filter(x=>x.dni==dni)});
});

// Servicio 2
app.get('/service/veraz', (req, res) => {
    const dni =  req.query.dni
    let data = [
        {dni:12345678,score:0.1},
        {dni:12345675,score:0.4},
        {dni:12345674,score:4},
        {dni:12345673,score:1.5},
        {dni:12345672,score:0.8},
        {dni:30123456,score:0.1}]
    res.status(200).json({ response: data.filter(x=>x.dni==dni)})


});

// Servicio 3
app.get('/service/renaper', (req, res) => {
    const dni =  req.query.dni

    console.log(`Received request for DNI: ${dni}`);

    let data = [
        {dni:12345678,isAuthorize:true},
        {dni:12345675,isAuthorize:false},
        {dni:12345674,isAuthorize:false},
        {dni:12345673,isAuthorize:false},
        {dni:12345672,isAuthorize:true},
        {dni:30123456,isAuthorize:true},]

    res.status(200).json({ response: data.filter(x=>x.dni==dni) });
});

// Puerto
const PORT = process.env.PORT || 3000;
app.listen(PORT, () => {
    console.log(`Servidor escuchando en el puerto ${PORT}`);
});
