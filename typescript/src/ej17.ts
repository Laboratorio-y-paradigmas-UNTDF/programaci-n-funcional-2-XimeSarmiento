// Ejercicio 17 — Integrador TypeScript (7 pts)
// Trazabilidad: F-33

export type Orden = {
  id: number;
  cliente: string;
  total: number;
  categoria: string;
  activa: boolean;
};

export type Result<T, E> = { status: "ok"; value: T } | { status: "error"; error: E };

// ok si activa Y total > 100. err("orden inactiva") o err("monto insuficiente").
export function clasificarOrden(o: Orden): Result<Orden, string> {
  return o.activa
    ? o.total > 100
      ? { status: "ok", value: o }
      : { status: "error", error: "monto insuficiente" }
    : { status: "error", error: "orden inactiva" };
}

// Partial: retorna fn que crea nueva orden con total reducido por porcentaje.
export function aplicarDescuento(porcentaje: number): (o: Orden) => Orden {
  return (o: Orden): Orden => ({
    ...o,
    total: o.total * (1 - porcentaje / 100),
  });
}

// Pipeline: clasificar → separar ok/err → descuento 10% a aprobadas → sumar totales.
export function procesarOrdenes(ordenes: Orden[]): {
  aprobadas: Orden[];
  rechazadas: string[];
  totalFinal: number;
} {
  const aprobadas: Orden[] = [];
  const rechazadas: string[] = [];
  let totalFinal = 0;

  for (const orden of ordenes) {
    const resultado = clasificarOrden(orden);
    if (resultado.status === "ok") {
      const ordenConDescuento = aplicarDescuento(10)(resultado.value);
      aprobadas.push(ordenConDescuento);
      totalFinal += ordenConDescuento.total;
    } else {
      rechazadas.push(resultado.error);
    }
  }

  return { aprobadas, rechazadas, totalFinal };
}
