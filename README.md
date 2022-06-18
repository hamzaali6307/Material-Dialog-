# Material-Dialog-
Material custom Dialog 

Its basic allert dialog with 3 buttons
 positive buttton
 negitive button 
 neutrall button
 success and failure images in alert dialog

How you can you this Lib:
  import the jar file or add import in your project, sync your project, 
  Minimum version is 21 API and max is 32 
  
 Code Lab sample:
 
  /*..
string value for in capital words or use my lib constant  object Constants
    
use this for image in dialog..
   const val SUCCESS = "SUCCESS"
   const val FAILURE = "FAILURE"
   const val DEFAULT = "DEFAULT"
 ..*/
 
    MaterialDialog.Builder(this).apply {
                setTitle("Pop-Up Title")
                setMessage("Message goes here")
                setCancelable(true)
                setDialogTypeImage(SUCCESS) 
                setPositiveButton("Blue Button") {
                    Log.e("TAG", "Positive")
                }
                setNegativeButton("Red Button") {
                    Log.e("TAG", "Negative")
                }
                setNeutralButton("Gray Button") {
                    Log.e("TAG", "Neutral")
                }
                show()
            }


This library is basic for alert dialog to learn abooutt creating custom own lllibraries, As i am working on SDK's in my working enviroment for so long for hardware testing for Mobile. This time i plan to create libraries and upload it publlically for other developer to use it, so i can contribute in mobile side development
Please view this llibrary and share your more suggestions to improve it 

Thanks: Hamza ali
