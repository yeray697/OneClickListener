What is OneClickListener?
=========
It's an android library (but it could be ported to another language) that help you to handle if the user click a View two times in a row, but the first time has not finished yet
How do I use it?
=========
Both are serializable classes, so it supports screen orientation change:
    
    private static final String CLICK_LISTENER = "clickListener";
    private static final String MULTIPLE_CLICK_LISTENER = "multipleClickListener";
    private OneClickListener oneClickListener;
    private OneClickMultiple oneClickMultiple;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        .   .   .
        if (savedInstanceState != null) {
            oneClickListener = (OneClickListener) savedInstanceState.getSerializable(CLICK_LISTENER);
            oneClickMultiple = (OneClickMultiple) savedInstanceState.getSerializable(MULTIPLE_CLICK_LISTENER);
        } else {
            oneClickListener = new OneClickListener();
            oneClickMultiple = new OneClickMultiple();
        }
        //Use it ...
        .   .   .
    }
    
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putSerializable(CLICK_LISTENER,oneClickListener);
        outState.putSerializable(MULTIPLE_CLICK_LISTENER,oneClickMultiple);
    }
### OneClickListener
You can handle if the user click a View two times in a row, but the first time has not finished yet
#### Methods
    
    //You must override it
    protected abstract void onOneClick();

    //This method is called if you want to do something when user clicks the View while the first time click is still running
    //You don't need to override it
    protected void onDoubleClick(){}
    
    //You have to call this method inside onOneClick() at the end, or if you are using a thread, put it when thread ends
    //You have to pass 'false'
    public void setClicked(boolean clicked)
    
    //Check if is clicked
    public boolean isClicked()
    
#### Example
    OneClickListener oneClickListener = new OneClickListener() {
      @Override
      protected void onOneClick() {
        oneClickListener.setClicked(false);
      }

      @Override
      protected void onDoubleClick() {
          //Do things
      }
    };
  btTest1.setOnClickListener(oneClickListener);
  
### OneClickMultiple
It works like OneClickListener, but in this way you can handle multiple view

#### Methods

    //Add and get a listener. Pass a unique name, View name for example, and a listener, that have onClick() and onDoubleClick()
    public View.OnClickListener addListener(String viewName, final OneClickMultipleListener listener)
    
    //Get a listener by his name
    public View.OnClickListener getListener(String viewName)
    
    //You have to call this method inside onOneClick() at the end, or if you are using a thread, put it when thread ends
    //You have to pass 'false'
    public void setClicked(boolean clicked)
    
    //Check if is clicked
    public boolean isClicked()
    
#### Example

    OneClickMultiple oneClickMultiple = new OneClickMultiple();
    btTest2.setOnClickListener(oneClickMultiple.addListener("btTest2", new OneClickMultipleListener() {
      @Override
      public void onClick() {
        oneClickMultiple.setClicked(false);
      }
      @Override
      public void onDoubleClick() {
      }
    }));

    btTest3.setOnClickListener(oneClickMultiple.addListener("btTest2", new OneClickMultipleListener() {
      @Override
      public void onClick() {
        oneClickMultiple.setClicked(false);
      }
      @Override
      public void onDoubleClick() {
      }
    }));
