namespace _2025광주_1과제_2.View
{
    partial class H_소개서추가
    {
        /// <summary>
        /// 필수 디자이너 변수입니다.
        /// </summary>
        private System.ComponentModel.IContainer components = null;

        /// <summary>
        /// 사용 중인 모든 리소스를 정리합니다.
        /// </summary>
        /// <param name="disposing">관리되는 리소스를 삭제해야 하면 true이고, 그렇지 않으면 false입니다.</param>
        protected override void Dispose(bool disposing)
        {
            if (disposing && (components != null))
            {
                components.Dispose();
            }
            base.Dispose(disposing);
        }

        #region Windows Form 디자이너에서 생성한 코드

        /// <summary>
        /// 디자이너 지원에 필요한 메서드입니다. 
        /// 이 메서드의 내용을 코드 편집기로 수정하지 마세요.
        /// </summary>
        private void InitializeComponent()
        {
            this.label3 = new System.Windows.Forms.Label();
            this.label2 = new System.Windows.Forms.Label();
            this.label1 = new System.Windows.Forms.Label();
            this.button1 = new System.Windows.Forms.Button();
            this.placeHolder3 = new _2025광주_1과제_2.MyControl.PlaceHolder();
            this.placeHolder2 = new _2025광주_1과제_2.MyControl.PlaceHolder();
            this.placeHolder1 = new _2025광주_1과제_2.MyControl.PlaceHolder();
            this.SuspendLayout();
            // 
            // label3
            // 
            this.label3.AutoSize = true;
            this.label3.Font = new System.Drawing.Font("맑은 고딕", 9.75F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, ((byte)(129)));
            this.label3.Location = new System.Drawing.Point(12, 132);
            this.label3.Name = "label3";
            this.label3.Size = new System.Drawing.Size(34, 17);
            this.label3.TabIndex = 7;
            this.label3.Text = "내용";
            // 
            // label2
            // 
            this.label2.AutoSize = true;
            this.label2.Font = new System.Drawing.Font("맑은 고딕", 9.75F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, ((byte)(129)));
            this.label2.Location = new System.Drawing.Point(12, 86);
            this.label2.Name = "label2";
            this.label2.Size = new System.Drawing.Size(47, 17);
            this.label2.TabIndex = 7;
            this.label2.Text = "부제목";
            // 
            // label1
            // 
            this.label1.AutoSize = true;
            this.label1.Font = new System.Drawing.Font("맑은 고딕", 9.75F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, ((byte)(129)));
            this.label1.Location = new System.Drawing.Point(12, 34);
            this.label1.Name = "label1";
            this.label1.Size = new System.Drawing.Size(34, 17);
            this.label1.TabIndex = 7;
            this.label1.Text = "제목";
            // 
            // button1
            // 
            this.button1.BackColor = System.Drawing.Color.Gold;
            this.button1.FlatAppearance.BorderSize = 0;
            this.button1.FlatStyle = System.Windows.Forms.FlatStyle.Flat;
            this.button1.ForeColor = System.Drawing.Color.White;
            this.button1.Location = new System.Drawing.Point(88, 381);
            this.button1.Name = "button1";
            this.button1.Size = new System.Drawing.Size(272, 22);
            this.button1.TabIndex = 6;
            this.button1.Text = "작성하기";
            this.button1.UseVisualStyleBackColor = false;
            this.button1.Click += new System.EventHandler(this.button1_Click);
            // 
            // placeHolder3
            // 
            this.placeHolder3.Location = new System.Drawing.Point(88, 130);
            this.placeHolder3.Multiline = true;
            this.placeHolder3.Name = "placeHolder3";
            this.placeHolder3.place = "제목을 입력해주세요.";
            this.placeHolder3.Size = new System.Drawing.Size(272, 232);
            this.placeHolder3.TabIndex = 8;
            this.placeHolder3.TextChanged += new System.EventHandler(this.placeHolder3_TextChanged);
            // 
            // placeHolder2
            // 
            this.placeHolder2.Location = new System.Drawing.Point(88, 84);
            this.placeHolder2.Name = "placeHolder2";
            this.placeHolder2.place = "제목을 입력해주세요.";
            this.placeHolder2.Size = new System.Drawing.Size(272, 23);
            this.placeHolder2.TabIndex = 8;
            this.placeHolder2.TextChanged += new System.EventHandler(this.placeHolder2_TextChanged);
            // 
            // placeHolder1
            // 
            this.placeHolder1.Location = new System.Drawing.Point(88, 32);
            this.placeHolder1.Name = "placeHolder1";
            this.placeHolder1.place = "제목을 입력해주세요.";
            this.placeHolder1.Size = new System.Drawing.Size(272, 23);
            this.placeHolder1.TabIndex = 8;
            this.placeHolder1.TextChanged += new System.EventHandler(this.placeHolder1_TextChanged);
            // 
            // H_소개서추가
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(7F, 15F);
            this.ClientSize = new System.Drawing.Size(390, 428);
            this.Controls.Add(this.placeHolder3);
            this.Controls.Add(this.placeHolder2);
            this.Controls.Add(this.placeHolder1);
            this.Controls.Add(this.label3);
            this.Controls.Add(this.label2);
            this.Controls.Add(this.label1);
            this.Controls.Add(this.button1);
            this.Name = "H_소개서추가";
            this.Text = "소개서 작성";
            this.ResumeLayout(false);
            this.PerformLayout();

        }

        #endregion

        private System.Windows.Forms.Button button1;
        private System.Windows.Forms.Label label1;
        private System.Windows.Forms.Label label2;
        private System.Windows.Forms.Label label3;
        private MyControl.PlaceHolder placeHolder1;
        private MyControl.PlaceHolder placeHolder2;
        private MyControl.PlaceHolder placeHolder3;
    }
}
