package appconta;


public class Imposto
{ 
        //CONSTANTES
        final float PIS= 0.95F;
        final float PISE= 1.65F;
        final float COFINS= 4.39F;  
        final float CONFINSE= 7.60F;
        final float COSIP= 9.70F;
        final float COSIPE= 29.96F;
        final float VALOR_KWH_EM_REAL = 0.74F;
        final float VALOR_ICMS_91_200 = 12.00F;
        final float VALOR_ICMS_MAIOR_200 = 25.00F;
        final float VALOR_ICMS_SPUBLICO = 18.00F;
        final float VALOR_ICMS_OUTROS = 18.00F;
             
        private float a1_residencial(float valorReal)//valorReal é o dinheiro
        {
            float soma=0;
            soma += valorReal*PIS/100;
            soma += valorReal*COFINS/100;
            soma += valorReal*COSIP/100;
            
            return soma+valorReal; //juros soma + valor em  real 
        }

        private float a2_residencial_baixa_renda(float valorReal)
        {
            float soma=0;
            soma += valorReal*PIS/100;
            soma += valorReal*COFINS/100;
             
            return soma+valorReal;
        }

        private float b1_residencial_rual(float valorReal)
        {
            float soma=0;
            soma += valorReal*PIS/100;
            soma += valorReal*COFINS/100;
            soma += valorReal*COSIPE/100;
            
            return soma+valorReal;
        }

        private float d_servico_publico(float valorReal)
        {
            float soma=valorReal*PIS/100;
            return soma+valorReal;
        } 

        private float e_outros_servicos(float valorReal)
        {
            float soma=0;
            soma += valorReal*PISE/100;
            soma += valorReal*CONFINSE/100;
            soma += valorReal*COSIPE/100;
            
            return soma+valorReal;
        }
        
        
        public float ConverterKwhEmReais(int pKwh)
        {
            return pKwh*VALOR_KWH_EM_REAL;
        }
        
        public float ConverterReaisEmKwh(float pValorReal)
        {
            return pValorReal/VALOR_KWH_EM_REAL;
        }
        
        public float CalcularImpostoComICMS(int pkWh, SubGrupo pSubGrupo)  //neste metodo obriga o estagiario a informar o kw/h e o subgrupo
        {
            float valorKwhEmReal = ConverterKwhEmReais(pkWh);
            
            switch(pSubGrupo)//
            {
                case residencialRural:
                case residencial:
                
                float ValorKwhRealImposto;
                
                    if(pSubGrupo==SubGrupo.residencial)
                    {
                        ValorKwhRealImposto = a1_residencial(valorKwhEmReal);
                    }  
                    else
                    {
                      ValorKwhRealImposto = b1_residencial_rual(valorKwhEmReal);
                    }
                    
                    if(pkWh<=90)
                    {
                       return ValorKwhRealImposto; //isento do ICMS
                    }
                    else if(pkWh>=91 && pkWh<=200)
                    {
                        return ValorKwhRealImposto += valorKwhEmReal * VALOR_ICMS_91_200/100;  //12% de juros do ICMS
                    }
                    else
                    {
                        return ValorKwhRealImposto += valorKwhEmReal * VALOR_ICMS_MAIOR_200/100; //acima de 200 cobra 25% ICMS
                    }
                                        
                case residencialBaixaRenda:
                    return a2_residencial_baixa_renda(valorKwhEmReal);
                                                           
                case servicoPublico:
                    float soma;
                    soma = d_servico_publico(valorKwhEmReal);
                    soma += (soma * VALOR_ICMS_SPUBLICO/100);
                    return soma;
                    
                case outros:
                    soma = e_outros_servicos(valorKwhEmReal);
                    soma += (soma * VALOR_ICMS_OUTROS/100);
                    return soma;
                    
                default :
                    return valorKwhEmReal;
            }
        }
    
    public enum SubGrupo //Tipos de subgrupo para usar no metodo acima.
    {
        residencial ,
        residencialBaixaRenda,
        residencialRural,
        servicoPublico,
        outros;
    }
}