using UnityEngine;

public class ContrlolertoSnowE : MonoBehaviour {

/*

ALÉM DE VERIFICAR A COLISÃO E FALA QUE É PRA MOSTRAR O BALÃO 


*/

        public GameObject BallonFont;
    protected bool isOnHere;
    private void OnTriggerEnter2D(Collider2D other) {
         isOnHere = true;
         BallonFont.SetActive(true);
    }
    private void OnTriggerExit2D(Collider2D other) {
        isOnHere = false;
        BallonFont.SetActive(false);
        //Debug.Log("Játáaqui");
    }
}