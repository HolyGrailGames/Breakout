package is.ru.tgra;

public class ModelMatrix extends Matrix {
	float[] M2;
	
	public ModelMatrix() {
		super();
		M2 = new float[16];
	}
	
	public void addTranslation(float Tx, float Ty, float Tz) {
		matrix.put(12, matrix.get(0)*Tx + matrix.get(4)*Ty + matrix.get(8)*Tz + matrix.get(12));
		matrix.put(13, matrix.get(1)*Tx + matrix.get(5)*Ty + matrix.get(9)*Tz + matrix.get(13));
		matrix.put(14, matrix.get(2)*Tx + matrix.get(6)*Ty + matrix.get(10)*Tz + matrix.get(14));
	}
	
	public void addScale(float Sx, float Sy, float Sz) {
		matrix.put(0, Sx * matrix.get(0));
		matrix.put(1, Sx * matrix.get(1));
		matrix.put(2, Sx * matrix.get(2));
		
		matrix.put(4, Sy * matrix.get(4));
		matrix.put(5, Sy * matrix.get(5));
		matrix.put(6, Sy * matrix.get(6));
		
		matrix.put(8, Sz * matrix.get(8));
		matrix.put(9, Sz * matrix.get(9));
		matrix.put(10, Sz * matrix.get(10));
	}
	
	public void addRotationX(float angle) {
		float c = (float)Math.cos((double)angle * Math.PI / 180.0);
		float s = (float)Math.sin((double)angle * Math.PI / 180.0);
		
		M2[0] = 1; M2[4] = 0; M2[8] = 0; M2[12] = 0;
		M2[1] = 0; M2[5] = c; M2[9] = -s; M2[13] = 0;
		M2[2] = 0; M2[6] = s; M2[10] = c; M2[14] = 0;
		M2[3] = 0; M2[7] = 0; M2[11] = 0; M2[15] = 1;
		
		this.addTransformation(M2);
	}
	
	public void addRotationY(float angle) {
		float c = (float)Math.cos((double)angle * Math.PI / 180.0);
		float s = (float)Math.sin((double)angle * Math.PI / 180.0);
		
		M2[0] = c; M2[4] = 0; M2[8] = s; M2[12] = 0;
		M2[1] = 0; M2[5] = 1; M2[9] = 0; M2[13] = 0;
		M2[2] = -s; M2[6] = 0; M2[10] = c; M2[14] = 0;
		M2[3] = 0; M2[7] = 0; M2[11] = 0; M2[15] = 1;
		
		this.addTransformation(M2);
	}
	
	public void addRotationZ(float angle) {
		float c = (float)Math.cos((double)angle * Math.PI / 180.0);
		float s = (float)Math.sin((double)angle * Math.PI / 180.0);
		
		M2[0] = c; M2[4] = -s; M2[8] = 0; M2[12] = 0;
		M2[1] = s; M2[5] = c; M2[9] = 0; M2[13] = 0;
		M2[2] = 0; M2[6] = 0; M2[10] = 1; M2[14] = 0;
		M2[3] = 0; M2[7] = 0; M2[11] = 0; M2[15] = 1;
		
		this.addTransformation(M2);
	}
}
