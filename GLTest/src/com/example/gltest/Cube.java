package com.example.gltest;

import java.nio.FloatBuffer;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;


import android.opengl.GLES20;
import android.opengl.Matrix;

public class Cube {
	
    private FloatBuffer mVertexBuffer;
    private FloatBuffer mColorBuffer;
    private ByteBuffer  mIndexBuffer;
        
    private float vertices[] = {
                                -1.0f, -1.0f, -1.0f,
                                1.0f, -1.0f, -1.0f,
                                1.0f,  1.0f, -1.0f,
                                -1.0f, 1.0f, -1.0f,
                                -1.0f, -1.0f,  1.0f,
                                1.0f, -1.0f,  1.0f,
                                1.0f,  1.0f,  1.0f,
                                -1.0f,  1.0f,  1.0f
                                };
    private float colors[] = {
                               0.0f,  1.0f,  0.0f,  1.0f,
                               0.0f,  1.0f,  0.0f,  1.0f,
                               1.0f,  0.5f,  0.0f,  1.0f,
                               1.0f,  0.5f,  0.0f,  1.0f,
                               1.0f,  0.0f,  0.0f,  1.0f,
                               1.0f,  0.0f,  0.0f,  1.0f,
                               0.0f,  0.0f,  1.0f,  1.0f,
                               1.0f,  0.0f,  1.0f,  1.0f
                            };
   
    private byte indices[] = {
                              0, 4, 5, 0, 5, 1,
                              1, 5, 6, 1, 6, 2,
                              2, 6, 7, 2, 7, 3,
                              3, 7, 4, 3, 4, 0,
                              4, 7, 6, 4, 6, 5,
                              3, 0, 1, 3, 1, 2
                              };
    
    public Cube() {
        ByteBuffer byteBuf = ByteBuffer.allocateDirect(vertices.length * 4);
        byteBuf.order(ByteOrder.nativeOrder());
        mVertexBuffer = byteBuf.asFloatBuffer();
        mVertexBuffer.put(vertices);
        mVertexBuffer.position(0);
            
        byteBuf = ByteBuffer.allocateDirect(colors.length * 4);
        byteBuf.order(ByteOrder.nativeOrder());
        mColorBuffer = byteBuf.asFloatBuffer();
        mColorBuffer.put(colors);
        mColorBuffer.position(0);
            
        mIndexBuffer = ByteBuffer.allocateDirect(indices.length);
        mIndexBuffer.put(indices);
        mIndexBuffer.position(0);
    }
    
    public void draw(final FloatBuffer aCubeBuffer) {             
    	// Pass in the position information
		aCubeBuffer.position(mPositionOffset);
        GLES20.glVertexAttribPointer(mPositionHandle, mPositionDataSize, GLES20.GL_FLOAT, false,
        		mStrideBytes, aTriangleBuffer);        
                
        GLES20.glEnableVertexAttribArray(mPositionHandle);        
        
        // Pass in the color information
        aTriangleBuffer.position(mColorOffset);
        GLES20.glVertexAttribPointer(mColorHandle, mColorDataSize, GLES20.GL_FLOAT, false,
        		mStrideBytes, aTriangleBuffer);        
        
        GLES20.glEnableVertexAttribArray(mColorHandle);
        
		// This multiplies the view matrix by the model matrix, and stores the result in the MVP matrix
        // (which currently contains model * view).
        Matrix.multiplyMM(mMVPMatrix, 0, mViewMatrix, 0, mModelMatrix, 0);
        
        // This multiplies the modelview matrix by the projection matrix, and stores the result in the MVP matrix
        // (which now contains model * view * projection).
        Matrix.multiplyMM(mMVPMatrix, 0, mProjectionMatrix, 0, mMVPMatrix, 0);

        GLES20.glUniformMatrix4fv(mMVPMatrixHandle, 1, false, mMVPMatrix, 0);
        GLES20.glDrawArrays(GLES20.GL_TRIANGLES, 0, 3);                               
}
                

}
