package edu.eam.ingesoft.logica.credito;

/**
 * Clase que representa una evaluación de crédito para la entidad financiera FinAurora.
 * Permite calcular cuotas mensuales y evaluar la aprobación de créditos según reglas de negocio.
 */
public class EvaluacionCredito {
    
    // TODO: Declarar los atributos necesarios para la evaluación de crédito
    // Según el contexto de negocio, necesitas:
    // - Nombre del solicitante (String)
    // - Ingresos mensuales (double)
    // - Número de créditos activos (int)
    // - Puntaje de crédito (int, rango 0-1000)
    // - Valor del crédito solicitado (double)
    // - Tiene codeudor (boolean)
    
    /**
     * Constructor de la clase EvaluacionCredito.
     * 
     * @param nombreSolicitante Nombre completo del solicitante del crédito
     * @param ingresosMensuales Ingresos mensuales del solicitante en pesos
     * @param numeroCreditosActivos Cantidad de créditos activos que tiene el solicitante
     * @param puntajeCredito Puntaje crediticio del solicitante (0-1000)
     * @param valorCreditoSolicitado Monto del crédito solicitado en pesos
     * @param tieneCodedor Indica si el solicitante cuenta con un codeudor
     */
    public EvaluacionCredito(String nombreSolicitante, double ingresosMensuales, 
                            int numeroCreditosActivos, int puntajeCredito, 
                            double valorCreditoSolicitado, boolean tieneCodedor) {
        // TODO: Inicializar los atributos con los parámetros recibidos
    }
    
    /**
     * Calcula la tasa de interés mensual a partir de la tasa nominal anual.
     * 
     * @param tasaNominalAnual Tasa nominal anual en porcentaje
     * @return Tasa mensual en porcentaje
     */
    public double calcularTasaMensual(double tasaNominalAnual) {
        // TODO: Implementar el cálculo de la tasa mensual
        // Fórmula: TNA / 12
        return 0; // Cambiar por la implementación correcta
    }
    
    /**
     * Calcula la cuota mensual del crédito usando la fórmula de amortización francesa.
     * Formula: Cuota = M * (im * (1+im)^n) / ((1+im)^n - 1)
     * 
     * @param tasaNominalAnual Tasa nominal anual en porcentaje
     * @param plazoMeses Plazo del crédito en meses
     * @return Valor de la cuota mensual en pesos
     */
    public double calcularCuotaMensual(double tasaNominalAnual, int plazoMeses) {
        // TODO: Implementar el cálculo de la cuota mensual
        // Recuerda:
        // - Usar calcularTasaMensual() para obtener la tasa mensual
        // - Convertir la tasa a decimal dividiendo entre 100
        // - Caso especial: si la tasa es 0, la cuota = monto / plazo
        // - Usar Math.pow() para las potencias
        return 0; // Cambiar por la implementación correcta
    }
    
    /**
     * Evalúa si el crédito debe ser aprobado según las reglas de negocio:
     * - Perfil bajo (puntaje < 500): Rechazo automático
     * - Perfil medio (500 ≤ puntaje ≤ 700): Requiere codeudor y cuota ≤ 25% de ingresos
     * - Perfil alto (puntaje > 700 y < 2 créditos): Cuota ≤ 30% de ingresos
     * 
     * @param tasaNominalAnual Tasa nominal anual en porcentaje
     * @param plazoMeses Plazo del crédito en meses
     * @return true si el crédito es aprobado, false si es rechazado
     */
    public boolean evaluarAprobacion(double tasaNominalAnual, int plazoMeses) {
        // TODO: Implementar la lógica de evaluación
        // Pasos recomendados:
        // 1. Calcular la cuota mensual usando calcularCuotaMensual()
        // 2. Determinar el perfil según el puntaje de crédito
        // 3. Aplicar las reglas correspondientes a cada perfil
        // 4. Calcular el porcentaje de la cuota respecto a los ingresos
        return false; // Cambiar por la implementación correcta
    }
    
    // TODO: Implementar todos los getters y setters
    // Necesitas métodos get/set para todos los atributos:
    
    /**
     * Obtiene el nombre del solicitante.
     * @return Nombre completo del solicitante
     */
    public String getNombreSolicitante() {
        return null; // TODO: Implementar
    }
    
    /**
     * Establece el nombre del solicitante.
     * @param nombreSolicitante Nombre completo del solicitante
     */
    public void setNombreSolicitante(String nombreSolicitante) {
        // TODO: Implementar
    }
    
    // TODO: Continúa implementando los demás getters y setters...
    // getIngresosMensuales() / setIngresosMensuales()
    // getNumeroCreditosActivos() / setNumeroCreditosActivos()
    // getPuntajeCredito() / setPuntajeCredito()
    // getValorCreditoSolicitado() / setValorCreditoSolicitado()
    // isTieneCodedor() / setTieneCodedor()
    
    public double getIngresosMensuales() {
        return 0; // TODO: Implementar
    }
    
    public void setIngresosMensuales(double ingresosMensuales) {
        // TODO: Implementar
    }
    
    public int getNumeroCreditosActivos() {
        return 0; // TODO: Implementar
    }
    
    public void setNumeroCreditosActivos(int numeroCreditosActivos) {
        // TODO: Implementar
    }
    
    public int getPuntajeCredito() {
        return 0; // TODO: Implementar
    }
    
    public void setPuntajeCredito(int puntajeCredito) {
        // TODO: Implementar
    }
    
    public double getValorCreditoSolicitado() {
        return 0; // TODO: Implementar
    }
    
    public void setValorCreditoSolicitado(double valorCreditoSolicitado) {
        // TODO: Implementar
    }
    
    public boolean isTieneCodedor() {
        return false; // TODO: Implementar
    }
    
    public void setTieneCodedor(boolean tieneCodedor) {
        // TODO: Implementar
    }
}