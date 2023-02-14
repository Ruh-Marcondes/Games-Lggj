using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class Doors : MonoBehaviour
{
    public int numberDoor;
    // Start is called before the first frame update
    void Start()
    {
        
    }

    // Update is called once per frame
    void Update()
    {
        
    }

     private void OnTriggerEnter2D(Collider2D other) {
        
        switch (numberDoor)
        {
            case 1:
                Debug.Log("Na porta do D");
            break;  
            case 2:
                Debug.Log("Na porta do P");
            break;
            case 3:
                Debug.Log("Na porta do M");
            break;
            default:
            break;
        }
    }
 }
