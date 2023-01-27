package appconta;


public class Tarifa{

    int kwh, res;
    float te, tusd, media;
    float med1, med2, conversao;
    float t1, t2;

    public Tarifa() {
      
        t1 = 0.25f;
        t2 = 0.25f;
        conversao = (t1 + t2) * kwh;

    }
    
    // Faz o calculo de acordo com a classe recebida da Interação com o Usuário 
    // \n1-RESIDENCIAL.\n2-RESIDENCIAL BAIXA RENDA.\n3-RESIDENCIAL RURAL.\n4-SERVIÇO PÚBLICO.
    
    public float calculoKwhEmReais(int kwh, int classe) {
       
        int classe1 = classe;
        int kwh1 = kwh;
        te = kwh1 * t1;
        tusd = kwh1 * t2;
        
        switch (classe1) {
            case 1:
                te = kwh1 * t1;
                tusd = kwh1 * t2;
                this.media = te + tusd;
                break;
            case 2:
                if (kwh1 <= 30) {
                    med1 = tusd / 72;
                    med2 = te / 65;
                    this.media = (te + tusd) - (med1 + med2);
                    
                } else if (kwh1 >= 31 && kwh1 <= 101) {
                    med1 = tusd / 50;
                    med2 = te / 40;
                    this.media = (te + tusd) - (med1 + med2);
                    
                } else if (kwh1 >= 101 && kwh1 <= 220) {
                    med1 = tusd / 30;
                    med2 = te / 10;
                    this.media = (te + tusd) - (med1 + med2);
                    
                } else if (kwh1 > 220) {
                    med1 = tusd / 25;
                    med2 = te;
                    this.media = (te + tusd) - (med1 + med2);
                    
                }   break;
            case 3:
                med1 = tusd / 30;
                med2 = te / 20;
                this.media = (te + tusd) - (med1 + med2);
                break;
            case 4:
                med1 = tusd / 40;
                med2 = te / 40;
                this.media = (te + tusd) - (med1 + med2);
                
                break;
            default:
                media = 0.0f;
                break;
        }

        return this.media;
    }
    
    public String mediaFinal(){
        String resultado = "";
        
        double newMedia = Math.round(media*100) / 100.0;
        resultado += "O total das tarifas é de: \nR$ " + newMedia;


        return resultado;
    }

}