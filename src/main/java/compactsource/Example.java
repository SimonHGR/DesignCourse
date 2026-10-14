// not allowed a package -- code ends up in default/unnamed package
// in the unnamed module
// implicit import module java.base
// alongside this feature we get java.lang.IO class

String getMessage() {
  return "Message!";
}

void main() {
  IO.println("Hello World!");
  IO.println("Classname is unspecified, but: " + this.getClass().getName());
  IO.println("Classname is unspecified, but: " + this.getClass().getPackageName());
}
