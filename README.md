# Boleta Microservicio

## POST /boletas
Crea una boleta nueva.

### Body JSON
```json
{
  "idPago": 123,
  "idUsuario": 456,
  "montoNeto": 1000
}
```

> Los campos `impuestoIva`, `montoTotal` y `fechaEmision` son de solo lectura y serán calculados por el servicio.

## PUT /boletas/{idBoleta}
Actualiza una boleta existente.

### Body JSON
```json
{
  "idPago": 123,
  "idUsuario": 456,
  "montoNeto": 1200
}
```
