package edu.eam.ingesoft.logica.credito;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.*;

public class EvaluacionCreditoTest {
    
    private EvaluacionCredito evaluacion;
    
    @BeforeEach
    public void setUp() {
        evaluacion = new EvaluacionCredito(
            "Juan Pérez",
            5000000,
            1,
            750,
            10000000,
            false
        );
    }
    
    @Test
    @DisplayName("Test 1: Calcular tasa mensual correctamente")
    public void testCalcularTasaMensual() {
        double tasaAnual = 24.0;
        double tasaMensual = evaluacion.calcularTasaMensual(tasaAnual);
        assertEquals(2.0, tasaMensual, 0.001, "La tasa mensual debe ser 2% cuando la anual es 24%");
    }
    
    @Test
    @DisplayName("Test 2: Calcular cuota mensual con tasa positiva")
    public void testCalcularCuotaMensualConTasa() {
        evaluacion.setValorCreditoSolicitado(1000000);
        double tasaAnual = 12.0;
        int plazoMeses = 12;
        double cuotaMensual = evaluacion.calcularCuotaMensual(tasaAnual, plazoMeses);
        assertTrue(cuotaMensual > 0, "La cuota mensual debe ser positiva");
        assertTrue(cuotaMensual > 83333, "La cuota debe incluir intereses");
    }
    
    @Test
    @DisplayName("Test 3: Calcular cuota mensual con tasa cero")
    public void testCalcularCuotaMensualSinTasa() {
        evaluacion.setValorCreditoSolicitado(1200000);
        double tasaAnual = 0;
        int plazoMeses = 12;
        double cuotaMensual = evaluacion.calcularCuotaMensual(tasaAnual, plazoMeses);
        assertEquals(100000, cuotaMensual, 0.01, "Sin intereses, la cuota debe ser el monto dividido el plazo");
    }
    
    @Test
    @DisplayName("Test 4: Rechazo automático por puntaje bajo (< 500)")
    public void testRechazoPerfilBajo() {
        evaluacion.setPuntajeCredito(450);
        evaluacion.setIngresosMensuales(10000000);
        evaluacion.setValorCreditoSolicitado(1000000);
        boolean aprobado = evaluacion.evaluarAprobacion(12, 12);
        assertFalse(aprobado, "Debe rechazar créditos con puntaje menor a 500");
    }
    
    @Test
    @DisplayName("Test 5: Perfil alto aprobado (puntaje > 700, cuota <= 30%)")
    public void testAprobacionPerfilAlto() {
        evaluacion.setPuntajeCredito(800);
        evaluacion.setNumeroCreditosActivos(1);
        evaluacion.setIngresosMensuales(10000000);
        evaluacion.setValorCreditoSolicitado(10000000);
        boolean aprobado = evaluacion.evaluarAprobacion(12, 48);
        assertTrue(aprobado, "Perfil alto con cuota menor al 30% debe ser aprobado");
    }
    
    @Test
    @DisplayName("Test 6: Perfil alto rechazado por cuota > 30%")
    public void testRechazoPerfilAltoPorCuota() {
        evaluacion.setPuntajeCredito(800);
        evaluacion.setNumeroCreditosActivos(0);
        evaluacion.setIngresosMensuales(1000000);
        evaluacion.setValorCreditoSolicitado(5000000);
        boolean aprobado = evaluacion.evaluarAprobacion(24, 12);
        assertFalse(aprobado, "Perfil alto con cuota mayor al 30% debe ser rechazado");
    }
    
    @Test
    @DisplayName("Test 7: Perfil medio sin codeudor - rechazado")
    public void testRechazoPerfilMedioSinCodedor() {
        evaluacion.setPuntajeCredito(600);
        evaluacion.setTieneCodedor(false);
        evaluacion.setIngresosMensuales(5000000);
        evaluacion.setValorCreditoSolicitado(1000000);
        boolean aprobado = evaluacion.evaluarAprobacion(12, 12);
        assertFalse(aprobado, "Perfil medio sin codeudor debe ser rechazado");
    }
    
    @Test
    @DisplayName("Test 8: Perfil medio con codeudor y cuota <= 25% - aprobado")
    public void testAprobacionPerfilMedioConCodedor() {
        evaluacion.setPuntajeCredito(600);
        evaluacion.setTieneCodedor(true);
        evaluacion.setIngresosMensuales(10000000);
        evaluacion.setValorCreditoSolicitado(10000000);
        boolean aprobado = evaluacion.evaluarAprobacion(12, 60);
        assertTrue(aprobado, "Perfil medio con codeudor y cuota <= 25% debe ser aprobado");
    }
    
    @Test
    @DisplayName("Test 9: Perfil medio con codeudor pero cuota > 25% - rechazado")
    public void testRechazoPerfilMedioConCodedorPorCuota() {
        evaluacion.setPuntajeCredito(600);
        evaluacion.setTieneCodedor(true);
        evaluacion.setIngresosMensuales(1000000);
        evaluacion.setValorCreditoSolicitado(3000000);
        boolean aprobado = evaluacion.evaluarAprobacion(24, 12);
        assertFalse(aprobado, "Perfil medio con cuota > 25% debe ser rechazado aunque tenga codeudor");
    }
    
    @Test
    @DisplayName("Test 10: Puntaje límite 500 con codeudor")
    public void testPuntajeLimite500() {
        evaluacion.setPuntajeCredito(500);
        evaluacion.setTieneCodedor(true);
        evaluacion.setIngresosMensuales(10000000);
        evaluacion.setValorCreditoSolicitado(1000000);
        boolean aprobado = evaluacion.evaluarAprobacion(12, 12);
        assertTrue(aprobado, "Puntaje 500 con codeudor y cuota baja debe ser aprobado");
    }
    
    @Test
    @DisplayName("Test 11: Puntaje límite 700 con codeudor")
    public void testPuntajeLimite700() {
        evaluacion.setPuntajeCredito(700);
        evaluacion.setTieneCodedor(true);
        evaluacion.setIngresosMensuales(10000000);
        evaluacion.setValorCreditoSolicitado(1000000);
        boolean aprobado = evaluacion.evaluarAprobacion(12, 12);
        assertTrue(aprobado, "Puntaje 700 con codeudor y cuota baja debe ser aprobado");
    }
    
    @Test
    @DisplayName("Test 12: Perfil alto con 2 o más créditos activos")
    public void testPerfilAltoConMuchosCreditosActivos() {
        evaluacion.setPuntajeCredito(800);
        evaluacion.setNumeroCreditosActivos(2);
        evaluacion.setIngresosMensuales(10000000);
        evaluacion.setValorCreditoSolicitado(1000000);
        boolean aprobado = evaluacion.evaluarAprobacion(12, 12);
        assertFalse(aprobado, "Perfil alto con 2 o más créditos activos no aplica para la regla del 30%");
    }
    
    @Test
    @DisplayName("Test 13: Validación getters y setters")
    public void testGettersSetters() {
        evaluacion.setNombreSolicitante("María García");
        assertEquals("María García", evaluacion.getNombreSolicitante());
        
        evaluacion.setIngresosMensuales(3000000);
        assertEquals(3000000, evaluacion.getIngresosMensuales(), 0.01);
        
        evaluacion.setNumeroCreditosActivos(3);
        assertEquals(3, evaluacion.getNumeroCreditosActivos());
        
        evaluacion.setPuntajeCredito(650);
        assertEquals(650, evaluacion.getPuntajeCredito());
        
        evaluacion.setValorCreditoSolicitado(15000000);
        assertEquals(15000000, evaluacion.getValorCreditoSolicitado(), 0.01);
        
        evaluacion.setTieneCodedor(true);
        assertTrue(evaluacion.isTieneCodedor());
    }
    
    @Test
    @DisplayName("Test 14: Cálculo de cuota con valores grandes")
    public void testCuotaValoresGrandes() {
        evaluacion.setValorCreditoSolicitado(100000000);
        double tasaAnual = 18;
        int plazoMeses = 120;
        double cuotaMensual = evaluacion.calcularCuotaMensual(tasaAnual, plazoMeses);
        assertTrue(cuotaMensual > 0, "La cuota debe ser positiva");
        assertTrue(cuotaMensual < 100000000, "La cuota no puede ser mayor al monto solicitado");
    }
    
    @Test
    @DisplayName("Test 15: Evaluación con todos los valores mínimos válidos")
    public void testValoresMinimos() {
        EvaluacionCredito evalMin = new EvaluacionCredito(
            "Test",
            1000,
            0,
            0,
            100,
            false
        );
        boolean aprobado = evalMin.evaluarAprobacion(12, 12);
        assertFalse(aprobado, "Con puntaje 0 debe ser rechazado");
    }
}