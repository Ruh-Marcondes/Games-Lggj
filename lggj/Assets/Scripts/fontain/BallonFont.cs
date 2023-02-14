using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class BallonFont : MonoBehaviour
{
   private GameObject keyE;
    public float alpha = 0.5f; //Isso é 50%
private void Start() {
    keyE = this.gameObject;
    
}
    private void Update() {
      
        
    }
    void OnTriggerEnter2D(Collider2D other) {
        Debug.Log("Tá no trigger do balão");
        ChangeAlpha(keyE.GetComponent<Renderer>().material,alpha);
    }
    private void OnTriggerExit2D(Collider2D other) {
         ChangeAlpha(keyE.GetComponent<Renderer>().material,1f);
    }

    void ChangeAlpha(Material mat, float alphaVal){
        Color  oldColor = mat.color;
        Color newColor = new Color (oldColor.r,oldColor.g,oldColor.b, alphaVal);
        mat.SetColor("_Color",newColor);
    }
 
}

