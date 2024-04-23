#!/bin/bash

# Función para generar un UUID aleatorio
generar_uuid() {
     echo -n "$(date +%s)-$RANDOM" | sha256sum | cut -d ' ' -f1
}

# Función para generar un número de tarjeta aleatorio
generar_tarjeta() {
    echo $((RANDOM % 10000000000000000 + 4000000000000000))
}

# Arreglos con valores posibles para diferentes campos
ciudades=("Madrid" "Barcelona" "Londres" "París" "Nueva York" "Tokio")
paises=("España" "Francia" "Estados Unidos" "Japón" "Reino Unido")
codigos_postales=("28001" "08001" "SW1A 1AA" "100-0001" "100-6722")
monedas=("Dolar" "Euro" "Libra" "Yen")
estados=("Aprobado" "Rechazado" "Pendiente")
tipos=("Compra" "Retiro" "Pago" "Transferencia")

# Función para seleccionar aleatoriamente un elemento de un arreglo
seleccionar_aleatorio() {
    local arr=("$@")
    echo "${arr[RANDOM % ${#arr[@]}]}"
}

# Generar transacción aleatoria
generar_transaccion() {
    local id=$(generar_uuid)
    local tarjeta=$(generar_tarjeta)
    local ciudad=$(seleccionar_aleatorio "${ciudades[@]}")
    local pais=$(seleccionar_aleatorio "${paises[@]}")
    local codigo_postal=$(seleccionar_aleatorio "${codigos_postales[@]}")
    local monto=$(awk -v min=10 -v max=1000 'BEGIN{srand(); print min+rand()*(max-min)}')
    local moneda=$(seleccionar_aleatorio "${monedas[@]}")
    local estado=$(seleccionar_aleatorio "${estados[@]}")
    local fecha=$(date -u +"%Y-%m-%dT%H:%M:%SZ")
    local tipo=$(seleccionar_aleatorio "${tipos[@]}")

    # Imprimir transacción como JSON
    printf '{"id": "%s", "tarjeta": "%s", "ciudad": "%s", "pais": "%s", "codigoPostal": "%s", "monto": %.2f, "moneda": "%s", "estado": "%s", "fecha": "%s", "tipo": "%s"}\n' "$id" "$tarjeta" "$ciudad" "$pais" "$codigo_postal" "$monto" "$moneda" "$estado" "$fecha" "$tipo"
}

# Verificar si se proporciona un argumento
if [ $# -ne 1 ]; then
    echo "Uso: $0 <cantidad>"
    exit 1
fi

# Obtener la cantidad de transacciones del primer argumento
cantidad=$1

# Generar y mostrar transacciones aleatorias según la cantidad especificada
for ((i = 0; i < cantidad; i++)); do
    generar_transaccion
done
