package appconta;

import static java.lang.System.exit;
import java.text.DecimalFormat;
import java.util.Scanner;


public class Cliente {
    public float clientes(){
        
        Scanner scan = new Scanner(System.in);
        Tarifa tarifa = new Tarifa();
        Imposto impostos = new Imposto();    
        int kwh;
        int classe;
        
        
        System.out.println("Informe os KWh: ");
        kwh = scan.nextInt();
        
        if (kwh < 50)
    {
           System.out.println("O valor mínimo de KWh aceito é de 50. Tente Novamente.");
           System.exit(0);
    }
        System.out.println("1-RESIDENCIAL.\n2-RESIDENCIAL BAIXA RENDA.\n3-RESIDENCIAL RURAL.\n4-SERVIÇO PÚBLICO");
        System.out.println("Informe a Classe de consumo do Cliente (valor de 1 a 5): ");
        classe = scan.nextInt();
        
        tarifa.calculoKwhEmReais(kwh, classe);
        System.out.println(tarifa.mediaFinal());
        System.out.println("O valor de sua conta sem impostos ficou:");    
        System.out.printf("R$ %.2f %n", impostos.ConverterKwhEmReais((int) kwh));  
        System.out.println("Valor de imposto pago:");
        System.out.printf("R$ %.2f %n", impostos.ConverterReaisEmKwh(classe));
        System.out.println("O valor de sua conta com impostos ficou:");
        System.out.printf("R$ %.2f %n", impostos.ConverterReaisEmKwh(classe) + impostos.ConverterKwhEmReais((int) kwh));  
        System.out.println("O valor de sua conta no total ficou: ");    
        System.out.printf("R$ %.2f %n", impostos.CalcularImpostoComICMS((int) kwh, Imposto.SubGrupo.outros));  
        exit(0);
        return 0;
    
 } 
}
